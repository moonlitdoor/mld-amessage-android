package com.moonlitdoor.amessage.connect.scan

import android.view.ViewGroup
import androidx.camera.core.CameraSelector
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.google.firebase.ml.vision.common.FirebaseVisionImageMetadata
import com.moonlitdoor.amessage.connect.ConnectViewModel
import com.moonlitdoor.amessage.connect.R
import com.moonlitdoor.amessage.constants.Constants
import com.moonlitdoor.amessage.domain.model.Profile
import com.moonlitdoor.amessage.extensions.Ensure
import timber.log.Timber
import java.util.concurrent.Executors
import androidx.camera.core.Preview as CameraPreview

@OptIn(ExperimentalPagerApi::class)
@ExperimentalGetImage
@Composable
fun Scan(
  viewModel: ConnectViewModel,
  isCurrentPage: Boolean,
) {

  val viewState by viewModel.scanViewState.collectAsState(ScanViewState.Scan)

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
        viewModel.executor = Executors.newSingleThreadExecutor()
        ProcessCameraProvider.getInstance(context).also { future ->
          future.addListener({
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
                  imageAnalysis.setAnalyzer(viewModel.executor, { imageProxy ->
                    imageProxy.image?.let { image ->
                      viewModel.detector.detectInImage(FirebaseVisionImage.fromMediaImage(image, FirebaseVisionImageMetadata.ROTATION_0))
                        .addOnSuccessListener { list ->
                          if (list.isEmpty()) {
                            imageProxy.close()
                          } else {
                            list.forEach { barcode ->
                              Timber.d("Scanning qr code found: ${barcode.rawValue}")
                              barcode.rawValue?.let { value ->
                                when {
                                  Constants.PROFILE_REGEX.toRegex().matches(value) -> viewModel.profileFound(Profile(value), imageProxy)
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
              CameraPreview.Builder()
                .build()
                .also {
                  it.setSurfaceProvider(previewView.surfaceProvider)
                }
            )
          }, ContextCompat.getMainExecutor(context))
        }
        previewView
      })
  }

  Ensure exhaustive when (viewState) {
    is ScanViewState.Scan -> {
    }
    is ScanViewState.Result.Connect -> ScanConnectDialog(viewModel = viewModel, viewState = viewState as ScanViewState.Result.Connect)
    is ScanViewState.Result.Connected -> ScanEnabledDialog(
      viewModel = viewModel,
      viewState = viewState as ScanViewState.Result,
      title = stringResource(id = R.string.connect_connected, (viewState as ScanViewState.Result.Connected).profile.handle)
    )
    is ScanViewState.Result.Experiments -> ScanExperimentsDialog(viewModel = viewModel, viewState = viewState as ScanViewState.Result.Experiments)
    is ScanViewState.Result.ExperimentsEnabled -> ScanEnabledDialog(viewModel = viewModel, viewState = viewState as ScanViewState.Result, title = stringResource(id = R.string.connect_experiments_enabled))
    is ScanViewState.Result.DeveloperSettings -> ScanDeveloperSettingsDialog(viewModel = viewModel, viewState = viewState as ScanViewState.Result.DeveloperSettings)
    is ScanViewState.Result.DeveloperSettingsEnabled -> ScanEnabledDialog(viewModel = viewModel, viewState = viewState as ScanViewState.Result, title = stringResource(id = R.string.connect_developer_settings_enabled))
    is ScanViewState.Result.EmployeeSettings -> ScanEmployeeSettingsDialog(viewModel = viewModel, viewState = viewState as ScanViewState.Result.EmployeeSettings)
    is ScanViewState.Result.EmployeeSettingsEnabled -> ScanEnabledDialog(viewModel = viewModel, viewState = viewState as ScanViewState.Result, title = stringResource(id = R.string.connect_employee_settings_enabled))
  }
}


@OptIn(ExperimentalPagerApi::class)
@ExperimentalGetImage
@Preview(showBackground = false)
@Composable
fun ScanPreview() {
  MaterialTheme {
    val pagerState = rememberPagerState(pageCount = 4)
    val viewModel: ConnectViewModel = viewModel()
    Scan(viewModel, true)
  }
}
