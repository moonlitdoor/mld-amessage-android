package com.moonlitdoor.amessage.connect

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.camera.core.CameraSelector
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcode
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcodeDetectorOptions
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.google.firebase.ml.vision.common.FirebaseVisionImageMetadata
import com.moonlitdoor.amessage.connect.databinding.FragmentScanBinding
import com.moonlitdoor.amessage.constants.Constants
import com.moonlitdoor.amessage.domain.model.Profile
import timber.log.Timber
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.inject.Inject

class ScanFragment : Fragment() {

  @Inject
  lateinit var viewModel: ConnectViewModel
  private val binding: FragmentScanBinding by lazy { FragmentScanBinding.inflate(layoutInflater, null, false) }
  private lateinit var cameraProvider: ProcessCameraProvider
  private lateinit var executor: ExecutorService
  private val detector = FirebaseVision.getInstance().getVisionBarcodeDetector(
    FirebaseVisionBarcodeDetectorOptions.Builder().setBarcodeFormats(FirebaseVisionBarcode.FORMAT_QR_CODE).build()
  )

  @ExperimentalGetImage
  override fun onAttach(context: Context) {
    super.onAttach(context)
    ConnectDI.get().inject(this)
    executor = Executors.newSingleThreadExecutor()
    ProcessCameraProvider.getInstance(requireContext()).also {
      it.addListener(Runnable {
        cameraProvider = it.get()
        cameraProvider.bindToLifecycle(this,
          CameraSelector.Builder()
            .requireLensFacing(CameraSelector.LENS_FACING_BACK)
            .build(),

          ImageAnalysis.Builder()
            .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
            .build().also { imageAnalysis ->
              imageAnalysis.setAnalyzer(executor, ImageAnalysis.Analyzer { imageProxy ->
                imageProxy.image?.let { image ->
                  detector.detectInImage(FirebaseVisionImage.fromMediaImage(image, FirebaseVisionImageMetadata.ROTATION_0))
                    .addOnSuccessListener { barcodes ->
                      if (barcodes.isNotEmpty()) {
                        for (barcode in barcodes) {
                          barcode.rawValue?.let { value ->
                            Timber.i(value)
                            if (Constants.EXPERIMENTS == value) {
//                              TODO("enable experiments")
                            } else {
                              val profile = Profile(value)
                              Timber.i(profile.toString())
                              viewModel.connect(profile)
                            }
                          }
                        }
                      }
                    }
                    .addOnFailureListener { e ->
                      Timber.e(e)
                    }
                }
              })
            },
          Preview.Builder().build().also { preview ->
            preview.setSurfaceProvider(binding.preview.previewSurfaceProvider)
          }
        )
      }, ContextCompat.getMainExecutor(requireContext()))
    }
  }

  override fun onDetach() {
    super.onDetach()
    executor.shutdown()
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) = binding.root

  companion object {
    val titleId = R.string.connect_scan_title
  }

}
