package com.moonlitdoor.amessage.connection

import androidx.compose.Composable
import androidx.ui.material.Button
import androidx.ui.material.ContainedButtonStyle
import androidx.ui.material.MaterialTheme
import androidx.ui.tooling.preview.Preview

@Composable
fun Connections(oC: (() -> Unit)? = null) {
  MaterialTheme {
    Button(
      text = "Settings",
      style = ContainedButtonStyle(),
      onClick = oC
    )
  }
}

@Preview
@Composable
fun ConnectionsPreview() {
  Connections()
}