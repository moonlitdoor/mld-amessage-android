package com.moonlitdoor.amessage.connections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import com.moonlitdoor.amessage.routes.Routes
import timber.log.Timber

@Composable
fun ConnectionsEmpty(navHostController: NavHostController) {
  Timber.d("Empty... ;(")
  Box(
    modifier = Modifier.fillMaxSize(),
    contentAlignment = Alignment.Center
  ) {
    Column(
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Center
    ) {
      Text(text = stringResource(id = R.string.connections_none))
      Spacer(modifier = Modifier.height(32.dp))
      OutlinedButton(onClick = { navHostController.navigate(Routes.Connect.route) }) {
        Text(text = stringResource(id = R.string.connections_connect))
      }
    }

  }

}