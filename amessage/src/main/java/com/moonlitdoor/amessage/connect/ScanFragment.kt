package com.moonlitdoor.amessage.connect

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.vision.CameraSource
import com.google.android.gms.vision.barcode.BarcodeDetector
import com.moonlitdoor.amessage.R
import com.moonlitdoor.amessage.components.TitledFragmentPagerAdapter
import com.moonlitdoor.amessage.constants.Constants
import com.moonlitdoor.amessage.databinding.FragmentScanBinding
import com.moonlitdoor.amessage.domain.model.Profile
import com.moonlitdoor.amessage.extensions.ignore
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*

class ScanFragment : TitledFragmentPagerAdapter.TitledFragment(), BarcodeProcessor.BarcodeDetectionListener {

  companion object {
    const val HANDLE_GMS = 5
    const val CAMERA_PERMISSION = 6
  }

  private val viewModel: ConnectViewModel by viewModel()
  private lateinit var binding: FragmentScanBinding

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
          startCameraSource()
        }
      } else {

        binding.preview.release()

      }
    }
  }

  override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    if (requestCode == CAMERA_PERMISSION && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
      startCameraSource()
    }
  }

  private fun startCameraSource() {
    val code = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(activity)
    if (code != ConnectionResult.SUCCESS) {
      GoogleApiAvailability.getInstance().getErrorDialog(activity, code, HANDLE_GMS).show()
    }

    binding.preview.start(
      CameraSource.Builder(activity, BarcodeDetector.Builder(activity).build().apply { setProcessor(BarcodeProcessor(this@ScanFragment)) })
        .setFacing(CameraSource.CAMERA_FACING_BACK)
        .setRequestedPreviewSize(resources.displayMetrics.widthPixels, resources.displayMetrics.heightPixels)
        .setRequestedFps(15.0f)
        .setAutoFocusEnabled(true)
        .build()
    )

  }

  override fun onNewBarcodeDetected(barcodeValue: String): Unit = when (barcodeValue) {
    Constants.EXPERIMENTS -> com.google.android.material.snackbar.Snackbar.make(binding.root, "Going to 'Experimental' mode.", com.google.android.material.snackbar.Snackbar.LENGTH_LONG).show()
    else -> {
      val parts = barcodeValue.split("|")
      if (parts.size == 5) {
        val profile = Profile(parts[0], parts[1], UUID.fromString(parts[2]), UUID.fromString(parts[3]), UUID.fromString(parts[4]))


      } else ignore()
    }
  }


}
