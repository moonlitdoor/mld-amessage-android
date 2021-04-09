package com.moonlitdoor.amessage.connect.scan

import androidx.camera.core.ImageProxy
import com.moonlitdoor.amessage.domain.model.Profile

sealed class ScanViewState() {

  object Scan : ScanViewState()
  sealed class Result(val imageProxy: ImageProxy) : ScanViewState() {

    class Connect(val profile: Profile, imageProxy: ImageProxy) : ScanViewState.Result(imageProxy)
    class Connected(val profile: Profile, imageProxy: ImageProxy) : ScanViewState.Result(imageProxy)
    class Experiments(imageProxy: ImageProxy) : ScanViewState.Result(imageProxy)
    class ExperimentsEnabled(imageProxy: ImageProxy) : ScanViewState.Result(imageProxy)
    class EmployeeSettings(imageProxy: ImageProxy) : ScanViewState.Result(imageProxy)
    class EmployeeSettingsEnabled(imageProxy: ImageProxy) : ScanViewState.Result(imageProxy)
    class DeveloperSettings(imageProxy: ImageProxy) : ScanViewState.Result(imageProxy)
    class DeveloperSettingsEnabled(imageProxy: ImageProxy) : ScanViewState.Result(imageProxy)

  }
}
