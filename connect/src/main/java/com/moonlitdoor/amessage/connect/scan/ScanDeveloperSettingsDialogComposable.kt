package com.moonlitdoor.amessage.connect.scan

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.moonlitdoor.amessage.connect.R
import timber.log.Timber

@Composable
fun ScanDeveloperSettingsDialog(viewState: ScanViewState.Result.DeveloperSettings, enableDeveloperSettings: () -> Unit, cancelCurrentScan: () -> Unit) {
  Timber.d("ScanDeveloperSettingsDialog Composable")
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
          enableDeveloperSettings()
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
