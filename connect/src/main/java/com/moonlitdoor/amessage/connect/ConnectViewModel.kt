package com.moonlitdoor.amessage.connect

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.common.BitMatrix
import com.moonlitdoor.amessage.domain.model.Connection
import com.moonlitdoor.amessage.domain.model.Profile
import com.moonlitdoor.amessage.domain.repository.ConnectionRepository
import com.moonlitdoor.amessage.domain.repository.ProfileRepository
import com.moonlitdoor.amessage.extensions.and
import com.moonlitdoor.amessage.extensions.map
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class ConnectViewModel @Inject constructor(private val connectionRepository: ConnectionRepository, profileRepository: ProfileRepository) : ViewModel() {

  private val profile = profileRepository.profile.and {
    Timber.i(it?.encode())
  }
  val qrCode: LiveData<Bitmap> = profile.map { encodeAsBitmap(it?.encode() ?: "null") }
  val pendingAndInvitedConnections = connectionRepository.getScannedInvitedAndPendingConnections()
  val selectedConnection = MutableLiveData<Connection>()

  @Suppress("UsePropertyAccessSyntax")
  fun setSelected(connection: Connection) = selectedConnection.setValue(connection)


  fun connect(profile: Profile) = viewModelScope.launch { connectionRepository.create(profile) }

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

  fun confirmConnection(connection: Connection) = viewModelScope.launch { connectionRepository.confirm(connection) }

  fun rejectConnection(connection: Connection) = viewModelScope.launch { connectionRepository.reject(connection) }

  companion object {

    private const val WHITE = -0x1
    private const val BLACK = -0x1000000
    private const val WIDTH = 500
    private const val HEIGHT = 500

  }
}