package com.moonlitdoor.amessage.connect

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.camera.core.*
import androidx.fragment.app.Fragment
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcode
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcodeDetectorOptions
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.google.firebase.ml.vision.common.FirebaseVisionImageMetadata
import com.moonlitdoor.amessage.connect.databinding.FragmentScanBinding
import com.moonlitdoor.amessage.domain.model.Profile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asExecutor
import timber.log.Timber
import javax.inject.Inject

class ScanFragment : Fragment(), Preview.OnPreviewOutputUpdateListener, ImageAnalysis.Analyzer {

  @Inject
  lateinit var viewModel: ConnectViewModel
  private lateinit var binding: FragmentScanBinding
  private val detector = FirebaseVision.getInstance().getVisionBarcodeDetector(
    FirebaseVisionBarcodeDetectorOptions.Builder().setBarcodeFormats(FirebaseVisionBarcode.FORMAT_QR_CODE).build()
  )
  private val preview = Preview(PreviewConfig.Builder().build()).also {
    it.setOnPreviewOutputUpdateListener(this)
  }
  private val analysis = ImageAnalysis(ImageAnalysisConfig.Builder().build()).also {
    it.setAnalyzer(Dispatchers.IO.asExecutor(), this)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    ConnectDI.get().inject(this)
  }

  override fun onAttach(context: Context) {
    super.onAttach(context)
    binding = FragmentScanBinding.inflate(layoutInflater, null, false)
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) = binding.root

  override fun onResume() {
    super.onResume()
    CameraX.bindToLifecycle(this, preview, analysis)
  }

  override fun onPause() {
    super.onPause()
    CameraX.unbindAll()
  }

  override fun onUpdated(output: Preview.PreviewOutput) {
    binding.preview.surfaceTexture = output.surfaceTexture
  }

  override fun analyze(image: ImageProxy?, rotationDegrees: Int) {
    image?.image?.let {
      detector.detectInImage(FirebaseVisionImage.fromMediaImage(it, FirebaseVisionImageMetadata.ROTATION_0))
        .addOnSuccessListener { barcodes ->
          if (barcodes.isNotEmpty()) {
            CameraX.unbind(analysis)
            for (barcode in barcodes) {
              barcode.rawValue?.let { value ->
                Timber.i(value)
                val profile = Profile(value)
                Timber.i(profile.toString())
                viewModel.connect(profile)
              }
            }
          }
        }
        .addOnFailureListener { e ->
          Timber.e(e)
        }
    }
  }

  companion object {
    val titleId = R.string.connect_scan_title
  }

}
