package com.moonlitdoor.amessage.connect.scan

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.camera.core.CameraSelector
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.android.gms.tasks.Task
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcode
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.google.firebase.ml.vision.common.FirebaseVisionImageMetadata
import com.moonlitdoor.amessage.connect.R
import com.moonlitdoor.amessage.constants.Constants
import com.moonlitdoor.amessage.domain.model.Connection
import com.moonlitdoor.amessage.extensions.Ensure
import timber.log.Timber
import java.util.concurrent.Executor
import androidx.camera.core.Preview as CameraPreview

@SuppressLint("UnsafeOptInUsageError")
@OptIn(ExperimentalPagerApi::class, ExperimentalGetImage::class)
@Composable
fun ScanPage(
  state: ScanViewState,
  isCurrentPage: Boolean,
  executor: Executor,
  detect: (FirebaseVisionImage) -> Task<List<FirebaseVisionBarcode>>,
  connectionFound: (Connection, ImageProxy) -> Unit,
  experimentsCodeFound: (ImageProxy) -> Unit,
  developerSettingsCodeFound: (ImageProxy) -> Unit,
  employeeSettingsCodeFound: (ImageProxy) -> Unit,
  createConnection: (Connection) -> Unit,
  enableExperiments: () -> Unit,
  enableDeveloperSettings: () -> Unit,
  enableEmployeeSettings: () -> Unit,
  cancelCurrentScan: () -> Unit,
) {
  Timber.d("Scan Composable")

  if (isCurrentPage) {
    CustomLifecycle.onResume()
  } else {
    CustomLifecycle.onPause()
  }

  Box(modifier = Modifier.fillMaxSize()) {
    AndroidView(
      modifier = Modifier.fillMaxSize(),
      factory = { context ->
        val previewView = PreviewView(context).also {
          it.scaleType = PreviewView.ScaleType.FILL_CENTER
          it.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
          )
          // Preview is incorrectly scaled in Compose on some devices without this
          it.implementationMode = PreviewView.ImplementationMode.COMPATIBLE
        }
        ProcessCameraProvider.getInstance(context).also { future ->
          future.addListener(
            {
              val cameraProvider = future.get()
              cameraProvider.unbindAll()
              cameraProvider.bindToLifecycle(
                CustomLifecycle,
                CameraSelector.Builder()
                  .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                  .build(),
                ImageAnalysis.Builder()
                  .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                  .build().also { imageAnalysis ->
                    imageAnalysis.setAnalyzer(
                      executor,
                      { imageProxy ->
                        imageProxy.image?.let { image ->
                          detect(FirebaseVisionImage.fromMediaImage(image, FirebaseVisionImageMetadata.ROTATION_0))
                            .addOnSuccessListener { list ->
                              if (list.isEmpty()) {
                                imageProxy.close()
                              } else {
                                list.forEach { barcode ->
                                  Timber.d("Scanning qr code found: ${barcode.rawValue}")
                                  barcode.rawValue?.let { value ->
                                    when {
                                      Constants.PROFILE_REGEX.toRegex().matches(value) -> connectionFound(Connection(value), imageProxy)
                                      value == Constants.EXPERIMENTS -> experimentsCodeFound(imageProxy)
                                      value == Constants.DEVELOPER_SETTINGS -> developerSettingsCodeFound(imageProxy)
                                      value == Constants.EMPLOYEE_SETTINGS -> employeeSettingsCodeFound(imageProxy)
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
                      }
                    )
                  },
                CameraPreview.Builder()
                  .build()
                  .also {
                    it.setSurfaceProvider(previewView.surfaceProvider)
                  }
              )
            },
            ContextCompat.getMainExecutor(context)
          )
        }
        previewView
      }
    )
  }

  Ensure exhaustive when (state) {
    is ScanViewState.Scan -> {
    }
    is ScanViewState.Result.Connect -> ScanConnectDialog(viewState = state, create = createConnection, cancelCurrentScan = cancelCurrentScan)
    is ScanViewState.Result.Connected -> ScanEnabledDialog(viewState = state, title = stringResource(id = R.string.connect_connected, state.connection.handle.value), cancelCurrentScan = cancelCurrentScan)
    is ScanViewState.Result.Experiments -> ScanExperimentsDialog(viewState = state, enableExperiments = enableExperiments, cancelCurrentScan = cancelCurrentScan)
    is ScanViewState.Result.ExperimentsEnabled -> ScanEnabledDialog(viewState = state as ScanViewState.Result, title = stringResource(id = R.string.connect_experiments_enabled), cancelCurrentScan = cancelCurrentScan)
    is ScanViewState.Result.DeveloperSettings -> ScanDeveloperSettingsDialog(viewState = state, enableDeveloperSettings = enableDeveloperSettings, cancelCurrentScan = cancelCurrentScan)
    is ScanViewState.Result.DeveloperSettingsEnabled -> ScanEnabledDialog(viewState = state, title = stringResource(id = R.string.connect_developer_settings_enabled), cancelCurrentScan = cancelCurrentScan)
    is ScanViewState.Result.EmployeeSettings -> ScanEmployeeSettingsDialog(viewState = state, enableEmployeeSettings = enableEmployeeSettings, cancelCurrentScan = cancelCurrentScan)
    is ScanViewState.Result.EmployeeSettingsEnabled -> ScanEnabledDialog(viewState = state, title = stringResource(id = R.string.connect_employee_settings_enabled), cancelCurrentScan = cancelCurrentScan)
  }
}

@Preview(showBackground = false)
@Composable
fun ScanPreview() {
  MaterialTheme {
  }
}
