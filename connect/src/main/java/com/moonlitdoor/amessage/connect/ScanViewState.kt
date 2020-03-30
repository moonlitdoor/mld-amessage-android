package com.moonlitdoor.amessage.connect

import androidx.camera.core.ImageProxy
import com.moonlitdoor.amessage.domain.model.Profile

sealed class ScanViewState(val imageProxy: ImageProxy) {

  class Connect(val profile: Profile, imageProxy: ImageProxy) : ScanViewState(imageProxy)

  class Connected(val profile: Profile, imageProxy: ImageProxy) : ScanViewState(imageProxy)

  class Experiments(imageProxy: ImageProxy) : ScanViewState(imageProxy)

  class ExperimentsEnabled(imageProxy: ImageProxy) : ScanViewState(imageProxy)

  class EmployeeSettings(imageProxy: ImageProxy) : ScanViewState(imageProxy)

  class EmployeeSettingsEnabled(imageProxy: ImageProxy) : ScanViewState(imageProxy)

  class DeveloperSettings(imageProxy: ImageProxy) : ScanViewState(imageProxy)

  class DeveloperSettingsEnabled(imageProxy: ImageProxy) : ScanViewState(imageProxy)

}