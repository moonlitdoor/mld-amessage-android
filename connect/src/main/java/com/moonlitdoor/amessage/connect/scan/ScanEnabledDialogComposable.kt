package com.moonlitdoor.amessage.connect.scan

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.moonlitdoor.amessage.connect.R
import timber.log.Timber

@Composable
fun ScanEnabledDialog(viewState: ScanViewState.Result, title: String, cancelCurrentScan: () -> Unit) {
  Timber.d("ScanEnabledDialog Composable")
  AlertDialog(
    onDismissRequest = {},
    title = {
      Text(text = title)
    },
    confirmButton = {
      Button(
        onClick = {
          viewState.imageProxy.close()
          cancelCurrentScan()
        }
      ) {
        Text(text = stringResource(id = R.string.connect_ok))
      }
    },
  )
}
