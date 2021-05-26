package com.moonlitdoor.amessage.news

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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import timber.log.Timber

@Composable
fun WhatsNew(navHostController: NavHostController, showBottomBar: (Boolean) -> Unit) {
  Timber.d("WhatsNew Composable")
  showBottomBar(false)
  Scaffold(
    topBar = {
      TopAppBar(
        title = {
          Text(text = stringResource(id = R.string.whats_new_title))
        },
        elevation = 12.dp,
        navigationIcon = {
          IconButton(onClick = { navHostController.popBackStack() }) {
            Icon(
              imageVector = Icons.Filled.ArrowBack,
              contentDescription = stringResource(R.string.connect_ok)
            )
          }
        },
      )
    },
  ) {
  }
}

@Preview
@Composable
fun WhatsNewPreview() {
  MaterialTheme {
    val navHostController = rememberNavController()
    WhatsNew(navHostController) { }
  }
}
