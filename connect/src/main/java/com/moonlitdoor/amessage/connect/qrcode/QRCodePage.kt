package com.moonlitdoor.amessage.connect.qrcode

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.moonlitdoor.amessage.components.Loading
import com.moonlitdoor.amessage.extensions.Ensure
import timber.log.Timber

@Composable
fun QRCodePage(state: QRCodeViewState) {
  Timber.d("QRCode Composable")
  Box(modifier = Modifier.fillMaxSize()) {
    Ensure exhaustive when (state) {
      is QRCodeViewState.Loading -> Loading("QR") { /* TODO pop the backstack */ }
      is QRCodeViewState.Empty -> QRCodeEmpty()
      is QRCodeViewState.Loaded -> QRCodeLoaded(viewState = state)
    }
  }
}
