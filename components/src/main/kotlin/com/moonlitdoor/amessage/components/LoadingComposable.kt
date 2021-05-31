package com.moonlitdoor.amessage.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.requiredSize
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
import com.moonlitdoor.amessage.theme.AMessageTheme
import timber.log.Timber

@Composable
fun Loading(@StringRes title: Int) = Loading(stringResource(id = title))

@Composable
fun Loading(title: String) {
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
        CircularProgressIndicator(modifier = Modifier.requiredSize(36.dp))
        Spacer(modifier = Modifier.height(8.dp))
        Text(stringResource(id = R.string.components_loading))
      }
    }
  }
}

@Preview(showBackground = true)
@Composable
fun LoadingPreview() {
  AMessageTheme {
    Loading("Loading")
  }
}
