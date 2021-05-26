package com.moonlitdoor.amessage.conversations

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.moonlitdoor.amessage.routes.Routes
import timber.log.Timber

@Composable
fun ConversationsEmpty(navHostController: NavHostController) {
  Timber.d("ConversationsEmpty Composable")
  Scaffold(
    topBar = {
      TopAppBar(
        title = {
          Text(text = stringResource(id = R.string.conversations_title))
        },
        elevation = 12.dp,
      )
    },
  ) {
    Box(
      modifier = Modifier.fillMaxSize(),
      contentAlignment = Alignment.Center
    ) {
      Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
      ) {
        Text(text = stringResource(id = R.string.conversations_none))
        Spacer(modifier = Modifier.height(32.dp))
        OutlinedButton(onClick = { navHostController.navigate(Routes.Connections.route) }) {
          Text(text = stringResource(id = R.string.connections_title))
        }
      }
    }
  }
}

@Preview(showBackground = true)
@Composable
fun ConversationsEmptyPreview() {
  val navHostController = rememberNavController()
  ConversationsEmpty(navHostController)
}
