package com.moonlitdoor.amessage.connect.scan

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.moonlitdoor.amessage.connect.ConnectViewModel
import com.moonlitdoor.amessage.connect.R

@Composable
fun ScanDeveloperSettingsDialog(viewModel: ConnectViewModel, viewState: ScanViewState.Result.DeveloperSettings) {
  AlertDialog(
    onDismissRequest = {},
    title = {
      Text(text = stringResource(id = R.string.connect_developer_settings))
    },
    text = {
      Text(text = stringResource(id = R.string.connect_enable_developer_settings))
    },
    confirmButton = {
      Button(
        onClick = {
          viewState.imageProxy.close()
          viewModel.enableDeveloperSettings()
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