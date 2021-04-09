package com.moonlitdoor.amessage.connect.scan

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.moonlitdoor.amessage.connect.ConnectViewModel
import com.moonlitdoor.amessage.connect.R

@Composable
fun ScanExperimentsDialog(viewModel: ConnectViewModel, viewState: ScanViewState.Result.Experiments) {
  AlertDialog(
    onDismissRequest = {},
    title = {
      Text(text = stringResource(id = R.string.connect_experiments))
    },
    text = {
      Text(text = stringResource(id = R.string.connect_enable_experiments))
    },
    confirmButton = {
      Button(
        onClick = {
          viewState.imageProxy.close()
          viewModel.enableExperiments()
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