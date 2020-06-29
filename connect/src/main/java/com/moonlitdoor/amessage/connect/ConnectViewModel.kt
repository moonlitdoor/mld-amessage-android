package com.moonlitdoor.amessage.connect

import android.graphics.Bitmap
import androidx.camera.core.ImageProxy
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.common.BitMatrix
import com.moonlitdoor.amessage.components.SingleLiveEvent
import com.moonlitdoor.amessage.domain.model.Connection
import com.moonlitdoor.amessage.domain.model.Profile
import com.moonlitdoor.amessage.domain.repository.ConnectionRepository
import com.moonlitdoor.amessage.domain.repository.ProfileRepository
import com.moonlitdoor.amessage.domain.repository.SettingsRepository
import com.moonlitdoor.amessage.extensions.and
import com.moonlitdoor.amessage.extensions.ignore
import com.moonlitdoor.amessage.extensions.map
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class ConnectViewModel @Inject constructor(private val connectionRepository: ConnectionRepository, private val settingsRepository: SettingsRepository, profileRepository: ProfileRepository) : ViewModel() {

  private val profile = profileRepository.getProfile().asLiveData().and {
    Timber.i(it.encode())
  }
  val qrCode: LiveData<Bitmap> = profile.map { encodeAsBitmap(it?.encode() ?: "null") }
  val pendingAndInvitedConnections = connectionRepository.getScannedInvitedAndPendingConnections()

  val selectedConnection = MutableLiveData<Connection>()
  val scanViewState = SingleLiveEvent<ScanViewState>()

  fun getInvitedConnections(): LiveData<List<Connection>> = connectionRepository.getInvitedConnections().asLiveData().also {
    it.observeForever {
      Timber.i(it.toString())
    }
  }

  val pending: LiveData<List<Connection>> = connectionRepository.getPending().asLiveData()

  val invited: LiveData<List<Connection>> = connectionRepository.invited.asLiveData()

  @Suppress("UsePropertyAccessSyntax")
  fun setSelected(connection: Connection) = selectedConnection.setValue(connection)

  fun connect(profile: Profile) = viewModelScope.launch(CoroutineExceptionHandler { _, exception ->
    println("CoroutineExceptionHandler got $exception")
  }) { connectionRepository.create(profile) }.ignore()

  private fun encodeAsBitmap(string: String): Bitmap {
    val result: BitMatrix = MultiFormatWriter().encode(string, BarcodeFormat.QR_CODE, WIDTH, HEIGHT, null)
    val w = result.width
    val h = result.height
    val pixels = IntArray(w * h)
    for (y in 0 until h) {
      val offset = y * w
      for (x in 0 until w) {
        pixels[offset + x] = if (result.get(x, y)) BLACK else WHITE
      }
    }
    return Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888).also { it.setPixels(pixels, 0, w, 0, 0, w, h) }
  }

  fun confirm(connection: Connection) = viewModelScope.launch(CoroutineExceptionHandler { _, exception ->
    println("CoroutineExceptionHandler got $exception")
  }) { connectionRepository.confirm(connection) }

  fun reject(connection: Connection) = viewModelScope.launch(CoroutineExceptionHandler { _, exception ->
    println("CoroutineExceptionHandler got $exception")
  }) { connectionRepository.reject(connection) }

  //TODO check of profile is already connected
  fun profileFound(profile: Profile, imageProxy: ImageProxy): Unit = scanViewState.setValue(ScanViewState.Connect(profile, imageProxy))

  fun experimentsCodeFound(imageProxy: ImageProxy) = if (settingsRepository.getExperimentsUiEnabled()) {
    scanViewState.setValue(ScanViewState.ExperimentsEnabled(imageProxy))
  } else {
    scanViewState.setValue(ScanViewState.Experiments(imageProxy))
  }

  fun enableExperiments() = settingsRepository.setExperimentsUiEnabled()

  fun developerSettingsCodeFound(imageProxy: ImageProxy) = if (settingsRepository.getDeveloperSettingsEnabled()) {
    scanViewState.setValue(ScanViewState.DeveloperSettingsEnabled(imageProxy))
  } else {
    scanViewState.setValue(ScanViewState.DeveloperSettings(imageProxy))
  }

  fun enableDeveloperSettings() = settingsRepository.setDeveloperSettingsEnabled()

  fun employeeSettingsCodeFound(imageProxy: ImageProxy) = if (settingsRepository.getEmployeeSettingsEnabled()) {
    scanViewState.setValue(ScanViewState.EmployeeSettingsEnabled(imageProxy))
  } else {
    scanViewState.setValue(ScanViewState.EmployeeSettings(imageProxy))
  }

  fun enableEmployeeSettings() = settingsRepository.setEmployeeSettingsEnabled()

  companion object {

    private const val WHITE = -0x1
    private const val BLACK = -0x1000000
    private const val WIDTH = 500
    private const val HEIGHT = 500

  }
}
