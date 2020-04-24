package com.moonlitdoor.amessage.connect

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.camera.core.CameraSelector
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.Observer
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

class ScanFragment : Fragment(), Observer<ScanViewState> {

  @Inject
  lateinit var viewModel: ConnectViewModel
  private val binding: FragmentScanBinding by lazy { FragmentScanBinding.inflate(layoutInflater, null, false) }
  private lateinit var cameraProvider: ProcessCameraProvider
  private lateinit var executor: ExecutorService
  private val detector = FirebaseVision.getInstance().getVisionBarcodeDetector(
    FirebaseVisionBarcodeDetectorOptions.Builder().setBarcodeFormats(FirebaseVisionBarcode.FORMAT_QR_CODE).build()
  )

  override fun onAttach(context: Context) {
    super.onAttach(context)
    ConnectDI.get(requireActivity()).inject(this)
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? = binding.root


  @ExperimentalGetImage
  override fun onResume() {
    super.onResume()
    viewModel.scanViewState.observe(this, this)
    CustomLifecycle.onResume()
    executor = Executors.newSingleThreadExecutor()
    ProcessCameraProvider.getInstance(requireContext()).also {
      it.addListener(Runnable {
        cameraProvider = it.get()
        val preview = Preview.Builder().build()
        val camera = cameraProvider.bindToLifecycle(
          CustomLifecycle,
          CameraSelector.Builder()
            .requireLensFacing(CameraSelector.LENS_FACING_BACK)
            .build(),
          ImageAnalysis.Builder()
            .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
            .build().also { imageAnalysis ->
              imageAnalysis.setAnalyzer(executor, ImageAnalysis.Analyzer { imageProxy ->
                imageProxy.image?.let { image ->
                  detector.detectInImage(FirebaseVisionImage.fromMediaImage(image, FirebaseVisionImageMetadata.ROTATION_0))
                    .addOnSuccessListener { list ->
                      if (list.isEmpty()) {
                        imageProxy.close()
                      } else {
                        list.forEach { barcode ->
                          Timber.v("barcode found: ${barcode.rawValue}")
                          barcode.rawValue?.let { value ->
                            when {
                              PROFILE_REGEX.matches(value) -> viewModel.profileFound(Profile(value), imageProxy)
                              value == Constants.EXPERIMENTS -> viewModel.experimentsCodeFound(imageProxy)
                              value == Constants.DEVELOPER_SETTINGS -> viewModel.developerSettingsCodeFound(imageProxy)
                              value == Constants.EMPLOYEE_SETTINGS -> viewModel.employeeSettingsCodeFound(imageProxy)
                              else -> Timber.i(value)
                            }
                          }
                        }
                      }
                    }
                    .addOnFailureListener { e ->
                      imageProxy.close()
                      Timber.e(e)
                    }
                }
              })
            },
          preview
        )
        preview.setSurfaceProvider(binding.preview.createSurfaceProvider(camera.cameraInfo))
      }, ContextCompat.getMainExecutor(requireContext()))
    }
  }

  override fun onPause() {
    super.onPause()
    CustomLifecycle.onPause()
    executor.shutdown()
  }

  override fun onChanged(viewState: ScanViewState) = when (viewState) {
    is ScanViewState.Connect -> AlertDialog.Builder(requireActivity())
      .setTitle("Connect")
      .setMessage("Connect with ${viewState.profile.handle}?")
      .setPositiveButton("OK") { _, _ ->
        viewState.imageProxy.close()
        viewModel.connect(viewState.profile)
      }
      .setNegativeButton("Cancel") { _, _ -> viewState.imageProxy.close() }
    is ScanViewState.Connected -> AlertDialog.Builder(requireActivity())
      .setTitle("Connected")
      .setMessage("Previously connected with ${viewState.profile.handle}")
      .setNeutralButton("OK") { _, _ -> viewState.imageProxy.close() }
    is ScanViewState.Experiments -> AlertDialog.Builder(requireActivity())
      .setTitle("Enable Experiments?")
      .setMessage("This is the message")
      .setPositiveButton("OK") { _, _ ->
        viewModel.enableExperiments()
        viewState.imageProxy.close()
      }
      .setNegativeButton("Cancel") { _, _ -> viewState.imageProxy.close() }
    is ScanViewState.ExperimentsEnabled -> AlertDialog.Builder(requireActivity())
      .setTitle("Experiments Enabled")
      .setMessage("This is the message")
      .setNeutralButton("OK") { _, _ -> viewState.imageProxy.close() }
    is ScanViewState.EmployeeSettings -> AlertDialog.Builder(requireActivity())
      .setTitle("Enable Employee Settings?")
      .setMessage("This is the message")
      .setPositiveButton("OK") { _, _ ->
        viewModel.enableEmployeeSettings()
        viewState.imageProxy.close()
      }
      .setNegativeButton("Cancel") { _, _ -> viewState.imageProxy.close() }
    is ScanViewState.EmployeeSettingsEnabled -> AlertDialog.Builder(requireActivity())
      .setTitle("Employee Settings Enabled")
      .setMessage("This is the message")
      .setNeutralButton("OK") { _, _ -> viewState.imageProxy.close() }
    is ScanViewState.DeveloperSettings -> AlertDialog.Builder(requireActivity())
      .setTitle("Enable Developer Settings?")
      .setMessage("This is the message")
      .setPositiveButton("OK") { _, _ ->
        viewModel.enableDeveloperSettings()
        viewState.imageProxy.close()
      }
      .setNegativeButton("Cancel") { _, _ -> viewState.imageProxy.close() }
    is ScanViewState.DeveloperSettingsEnabled -> AlertDialog.Builder(requireActivity())
      .setTitle("Developer Settings Enable")
      .setMessage("This is the message")
      .setNeutralButton("OK") { _, _ -> viewState.imageProxy.close() }

  }.create().show()


  companion object {
    val PROFILE_REGEX =
      """^.*\|.{163}\|[0-9a-f]{8}-[0-9a-f]{4}-4[0-9a-f]{3}-[0-9a-f]{4}-[0-9a-f]{12}\|[0-9a-f]{8}-[0-9a-f]{4}-4[0-9a-f]{3}-[0-9a-f]{4}-[0-9a-f]{12}\|[0-9a-f]{8}-[0-9a-f]{4}-4[0-9a-f]{3}-[0-9a-f]{4}-[0-9a-f]{12}$""".toRegex()
    val titleId = R.string.connect_scan_title
  }

  object CustomLifecycle : LifecycleOwner {
    private val lifecycleRegistry: LifecycleRegistry = LifecycleRegistry(this)

    fun onResume() {
      lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_CREATE)
      lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_START)
      lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
    }

    fun onPause() {
      lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_PAUSE)
      lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_STOP)
      lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    }

    override fun getLifecycle(): Lifecycle {
      return lifecycleRegistry
    }
  }

}
