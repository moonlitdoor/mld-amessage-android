package com.moonlitdoor.amessage.connections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import timber.log.Timber

@Composable
fun ConnectionsLoading() {
  Timber.d("Loading...")
  Box(
    modifier = Modifier.fillMaxSize(),
    contentAlignment = Alignment.Center,
  ) {
    Column(
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Center,
    ) {
      CircularProgressIndicator()
      Text(stringResource(id = R.string.connections_loading))
    }
  }
}

@Preview(showSystemUi = true)
@Composable
fun ConnectionsLoadingPreview() {
  ConnectionsLoading()
}