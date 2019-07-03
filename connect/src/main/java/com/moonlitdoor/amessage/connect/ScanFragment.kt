package com.moonlitdoor.amessage.connect

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.camera.core.*
import androidx.databinding.DataBindingUtil
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcode
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcodeDetectorOptions
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.google.firebase.ml.vision.common.FirebaseVisionImageMetadata
import com.moonlitdoor.amessage.components.TitledFragmentPagerAdapter
import com.moonlitdoor.amessage.connect.databinding.FragmentScanBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import timber.log.Timber

class ScanFragment : TitledFragmentPagerAdapter.TitledFragment(), Preview.OnPreviewOutputUpdateListener, ImageAnalysis.Analyzer {

  companion object {
    const val CAMERA_PERMISSION = 6
  }

  private val viewModel: ConnectViewModel by sharedViewModel()
  private lateinit var binding: FragmentScanBinding
  private val detector = FirebaseVision.getInstance().getVisionBarcodeDetector(
    FirebaseVisionBarcodeDetectorOptions.Builder().setBarcodeFormats(FirebaseVisionBarcode.FORMAT_QR_CODE).build()
  )
  private val preview = Preview(PreviewConfig.Builder().build()).also {
    it.onPreviewOutputUpdateListener = this
  }
  private val analysis = ImageAnalysis(ImageAnalysisConfig.Builder().build()).also {
    it.analyzer = this
  }

  override fun getTitleId() = R.string.connect_scan_title

  override fun onAttach(context: Context) {
    super.onAttach(context)
    binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_scan, null, false)
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) = binding.root

  override fun setUserVisibleHint(isVisibleToUser: Boolean) {
    super.setUserVisibleHint(isVisibleToUser)
    activity?.let {
      if (isVisibleToUser) {
        if (it.checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
          requestPermissions(arrayOf(Manifest.permission.CAMERA), CAMERA_PERMISSION)
        } else {
          CameraX.bindToLifecycle(this, preview, analysis)
        }
      } else {
        CameraX.unbindAll()
      }
    }
  }

  override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    if (requestCode == CAMERA_PERMISSION && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
      CameraX.bindToLifecycle(this, preview, analysis)
    }
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
              Timber.i(barcode.rawValue)
            }
          }
        }
        .addOnFailureListener { e ->
          Timber.e(e)
        }
    }
  }

}
