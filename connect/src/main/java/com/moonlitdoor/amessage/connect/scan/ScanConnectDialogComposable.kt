package com.moonlitdoor.amessage.connect.scan

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.moonlitdoor.amessage.connect.R
import com.moonlitdoor.amessage.domain.model.Connection
import timber.log.Timber

@Composable
fun ScanConnectDialog(viewState: ScanViewState.Result.Connect, create: (Connection) -> Unit, cancelCurrentScan: () -> Unit) {
  Timber.d("ScanConnectDialog Composable")
  AlertDialog(
    onDismissRequest = {},
    title = {
      Text(text = stringResource(id = R.string.connect_title))
    },
    text = {
      Text(text = stringResource(id = R.string.connect_with_handle, viewState.connection.handle.value))
    },
    confirmButton = {
      Button(
        onClick = {
          viewState.imageProxy.close()
          create(viewState.connection)
        }
      ) {
        Text(text = stringResource(id = R.string.connect_ok))
      }
    },
    dismissButton = {
      Button(
        onClick = {
          viewState.imageProxy.close()
          cancelCurrentScan()
        }
      ) {
        Text(text = stringResource(id = R.string.connect_cancel))
      }
    }
  )
}
