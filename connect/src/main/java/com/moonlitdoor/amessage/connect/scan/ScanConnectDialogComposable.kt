package com.moonlitdoor.amessage.connect.scan

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.moonlitdoor.amessage.connect.ConnectViewModel
import com.moonlitdoor.amessage.connect.R
import com.moonlitdoor.amessage.domain.model.Connection

@Composable
fun ScanConnectDialog(viewModel: ConnectViewModel, viewState: ScanViewState.Result.Connect) {
  AlertDialog(
    onDismissRequest = {},
    title = {
      Text(text = stringResource(id = R.string.connect_title))
    },
    text = {
      Text(text = stringResource(id = R.string.connect_with_handle, viewState.profile.handle))
    },
    confirmButton = {
      Button(
        onClick = {
          viewState.imageProxy.close()
          viewModel.confirm(Connection.from(viewState.profile))
        }) {
        Text(text = stringResource(id = R.string.connect_ok))
      }
    },
    dismissButton = {
      Button(
        onClick = {
          viewState.imageProxy.close()
          viewModel.cancelCurrentScan()
        }) {
        Text(text = stringResource(id = R.string.connect_cancel))
      }
    }
  )
}