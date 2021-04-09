package com.moonlitdoor.amessage.connect.qrcode

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moonlitdoor.amessage.connect.R
import kotlin.math.min

@Composable
fun QRCodeResult(viewState: QRCodeViewState.Result) {
  BoxWithConstraints(
    modifier = Modifier
      .fillMaxSize()
      .padding(16.dp),
    contentAlignment = Alignment.Center
  ) {
    val size = min(constraints.maxHeight, constraints.maxWidth)
    Image(
      modifier = Modifier
        .width(size.dp)
        .height(size.dp),
      bitmap = viewState.item,
      contentDescription = stringResource(id = R.string.connect_qr_code_title),
    )
  }
}

@Preview(showSystemUi = true)
@Composable
fun QRCodeResultPreview() {
  val viewState = QRCodeViewState.Result(
    item = ImageBitmap(500, 500)
  )
  QRCodeResult(viewState)
}
