package com.moonlitdoor.amessage.connect

import android.graphics.Bitmap
import androidx.camera.core.ImageProxy
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcode
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcodeDetectorOptions
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.common.BitMatrix
import com.moonlitdoor.amessage.connect.invited.InvitedViewState
import com.moonlitdoor.amessage.connect.pending.PendingViewState
import com.moonlitdoor.amessage.connect.qrcode.QRCodeViewState
import com.moonlitdoor.amessage.connect.scan.ScanViewState
import com.moonlitdoor.amessage.domain.model.Connection
import com.moonlitdoor.amessage.domain.repository.ConnectionRepository
import com.moonlitdoor.amessage.domain.repository.ProfileRepository
import com.moonlitdoor.amessage.domain.repository.SettingsRepository
import com.moonlitdoor.amessage.extensions.ignore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.time.Instant
import java.util.concurrent.ExecutorService
import javax.inject.Inject

@HiltViewModel
class ConnectViewModel @Inject constructor(
  private val connectionRepository: ConnectionRepository,
  profileRepository: ProfileRepository,
  private val settingsRepository: SettingsRepository
) : ViewModel() {

  lateinit var executor: ExecutorService

  val detector = FirebaseVision.getInstance().getVisionBarcodeDetector(
    FirebaseVisionBarcodeDetectorOptions.Builder().setBarcodeFormats(FirebaseVisionBarcode.FORMAT_QR_CODE).build()
  )

  val pendingViewState: Flow<PendingViewState> = connectionRepository.getPending().map {
    when (it.isNotEmpty()) {
      true -> PendingViewState.Result(it)
      false -> PendingViewState.Empty
    }
  }

  val invitedViewState: Flow<InvitedViewState> = connectionRepository.getInvited().map {
    when (it.isNotEmpty()) {
      true -> InvitedViewState.Result(it)
      false -> InvitedViewState.Empty
    }
  }

  val qrCodeViewState: Flow<QRCodeViewState> = profileRepository.getProfileFlow().map { profile ->
    profile?.let {
      QRCodeViewState.Result(encodeAsBitmap(it.encode()))
    } ?: QRCodeViewState.Empty
  }

  private val _scanViewState: MutableStateFlow<ScanViewState> = MutableStateFlow(ScanViewState.Scan)
  val scanViewState: StateFlow<ScanViewState> = _scanViewState.asStateFlow()

  fun connectionFound(connection: Connection, imageProxy: ImageProxy): Unit = viewModelScope.launch(Dispatchers.IO) {
    _scanViewState.emit(
      if (connectionRepository.isConnectionExisting(connection)) {
        ScanViewState.Result.Connected(connection, imageProxy)
      } else {
        ScanViewState.Result.Connect(connection, imageProxy)
      }
    )
  }.ignore()

  fun experimentsCodeFound(imageProxy: ImageProxy): Unit = viewModelScope.launch(Dispatchers.IO) {
    _scanViewState.emit(
      if (settingsRepository.isExperimentsUiEnabled()) {
        ScanViewState.Result.ExperimentsEnabled(imageProxy)
      } else {
        ScanViewState.Result.Experiments(imageProxy)
      }
    )
  }.ignore()

  fun developerSettingsCodeFound(imageProxy: ImageProxy): Unit = viewModelScope.launch(Dispatchers.IO) {
    _scanViewState.emit(
      if (settingsRepository.isDeveloperSettingsEnabled()) {
        ScanViewState.Result.DeveloperSettingsEnabled(imageProxy)
      } else {
        ScanViewState.Result.DeveloperSettings(imageProxy)
      }
    )
  }.ignore()

  fun employeeSettingsCodeFound(imageProxy: ImageProxy): Unit = viewModelScope.launch(Dispatchers.IO) {
    _scanViewState.emit(
      if (settingsRepository.isEmployeeSettingsEnabled()) {
        ScanViewState.Result.EmployeeSettingsEnabled(imageProxy)
      } else {
        ScanViewState.Result.EmployeeSettings(imageProxy)
      }
    )
  }.ignore()

  fun create(connection: Connection) = viewModelScope.launch(Dispatchers.IO) {
    connectionRepository.create(connection = connection)
    cancelCurrentScan()
  }

  fun confirm(connection: Connection) = viewModelScope.launch(Dispatchers.IO) { connectionRepository.confirm(connection = connection.copy(state = Connection.State.Connected, confirmed = Instant.now())) }

  fun reject(connection: Connection) = viewModelScope.launch(Dispatchers.IO) { connectionRepository.reject(connection = connection) }

  fun enableExperiments(): Unit = viewModelScope.launch(Dispatchers.IO) {
    settingsRepository.setExperimentsUiEnabled()
    cancelCurrentScan()
  }.ignore()

  fun enableDeveloperSettings(): Unit = viewModelScope.launch(Dispatchers.IO) {
    settingsRepository.setDeveloperSettingsEnabled()
    cancelCurrentScan()
  }.ignore()

  fun enableEmployeeSettings(): Unit = viewModelScope.launch(Dispatchers.IO) {
    settingsRepository.setEmployeeSettingsEnabled()
    cancelCurrentScan()
  }.ignore()

  fun cancelCurrentScan(): Unit = viewModelScope.launch(Dispatchers.IO) { _scanViewState.emit(ScanViewState.Scan) }.ignore()

  private fun encodeAsBitmap(string: String): ImageBitmap {
    val result: BitMatrix = MultiFormatWriter().encode(string, BarcodeFormat.QR_CODE, WIDTH, HEIGHT, null)
    val pixels = IntArray(WIDTH * HEIGHT)
    for (y in 0 until HEIGHT) {
      val offset = y * WIDTH
      for (x in 0 until WIDTH) {
        pixels[offset + x] = if (result.get(x, y))
          BLACK
        else
          WHITE
      }
    }
    return Bitmap.createBitmap(WIDTH, HEIGHT, Bitmap.Config.ARGB_8888).also { it.setPixels(pixels, 0, WIDTH, 0, 0, WIDTH, HEIGHT) }.asImageBitmap()
  }

  companion object {
    private const val WHITE = -0x1
    private const val BLACK = -0x1000000
    private const val WIDTH = 500
    private const val HEIGHT = 500
  }
}
