package com.moonlitdoor.amessage.about

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import timber.log.Timber

@Composable
fun AboutData(state: AboutScreenState.Data, popBackStack: () -> Unit) {
  Timber.d("AboutData")
  Scaffold(
    topBar = {
      TopAppBar(
        title = {
          Text(text = stringResource(id = R.string.about_title))
        },
        elevation = 12.dp,
        navigationIcon = {
          IconButton(onClick = popBackStack) {
            Icon(
              imageVector = Icons.Filled.ArrowBack,
              contentDescription = stringResource(R.string.connect_ok)
            )
          }
        },
      )
    },
  ) {
    Column {
      Text(text = state.version)
      Text(text = state.buildDate)
    }
  }
}

@Preview(showBackground = true)
@Composable
fun AboutData() {
  MaterialTheme {
    AboutData(
      AboutScreenState.Data(
        version = "version",
        buildDate = "buildDate"
      )
    ) {}
  }
}
