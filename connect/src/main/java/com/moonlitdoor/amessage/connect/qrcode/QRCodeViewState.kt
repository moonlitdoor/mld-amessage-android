package com.moonlitdoor.amessage.connect.qrcode

import androidx.compose.ui.graphics.ImageBitmap

sealed class QRCodeViewState {

  object Loading : QRCodeViewState()
  object Empty : QRCodeViewState()
  data class Loaded(val item: ImageBitmap) : QRCodeViewState()
}
