package com.moonlitdoor.amessage.connect.qrcode

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.moonlitdoor.amessage.components.Loading
import com.moonlitdoor.amessage.connect.ConnectViewModel
import com.moonlitdoor.amessage.extensions.Ensure
import timber.log.Timber

@Composable
fun QRCode(viewModel: ConnectViewModel) {
  Timber.d("QRCode Composable")
  Box(modifier = Modifier.fillMaxSize()) {
    val qrCodeState: QRCodeViewState by viewModel.qrCodeViewState.collectAsState(initial = QRCodeViewState.Loading)
    Ensure exhaustive when (qrCodeState) {
      is QRCodeViewState.Loading -> Loading()
      is QRCodeViewState.Empty -> QRCodeEmpty()
      is QRCodeViewState.Result -> QRCodeResult(viewState = qrCodeState as QRCodeViewState.Result)
    }
  }
}

@Preview(showBackground = false)
@Composable
fun QRCodePreview() {
  MaterialTheme {
    val viewModel: ConnectViewModel = viewModel()
    QRCode(viewModel)
  }
}
