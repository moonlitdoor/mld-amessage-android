package com.moonlitdoor.amessage.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import timber.log.Timber

@Composable
fun Loading(@StringRes title: Int) = Loading(stringResource(id = title))

@Composable
fun Loading(title: String = "Loading") {
  Timber.d("Loading Composable")
  Scaffold(
    topBar = {
      TopAppBar(
        title = {
          Text(text = title)
        },
        elevation = 12.dp,
      )
    },
  ) {
    Box(
      modifier = Modifier.fillMaxSize(),
      contentAlignment = Alignment.Center,
    ) {
      Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
      ) {
        CircularProgressIndicator()
        Text(stringResource(id = R.string.components_loading))
      }
    }
  }
}

@Preview(showSystemUi = true)
@Composable
fun LoadingPreview() {
  Loading()
}
