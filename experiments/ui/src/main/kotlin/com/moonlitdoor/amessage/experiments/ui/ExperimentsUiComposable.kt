package com.moonlitdoor.amessage.experiments.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.moonlitdoor.amessage.experiments.Experiments
import timber.log.Timber

@Composable
fun ExperimentsUi(navHostController: NavHostController, showBottomBar: (Boolean) -> Unit) {
  Timber.d("ExperimentsUi Composable")
  showBottomBar(false)
  Scaffold(
    topBar = {
      TopAppBar(
        title = {
          Text(text = stringResource(id = R.string.experiments_title))
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


    LazyColumn {
      items(Experiments.experiments) {
        Timber.d("$it")
        Box(
          modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
        ) {
          ExperimentsItem(item = it)
        }
      }
    }
  }
}

@Preview
@Composable
fun ExperimentsUiPreview() {
  val navHostController = rememberNavController()
  ExperimentsUi(navHostController) {}
}
