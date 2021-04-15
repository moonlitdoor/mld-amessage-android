package com.moonlitdoor.amessage.connection

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.moonlitdoor.amessage.components.AppChrome
import com.moonlitdoor.amessage.components.Navigation
import java.util.*

@Composable
fun Connection(navHostController: NavHostController, viewModel: ConnectionViewModel, connectionId: UUID, setAppChrome: (appChrome: AppChrome) -> Unit) {
  setAppChrome(
    AppChrome(
      title = stringResource(id = R.string.connection_title),
      navigation = Navigation(imageVector = Navigation.DEFAULT_ICON) { navHostController.popBackStack() }
    )
  )

  val connection by viewModel.getConnection(connectionId).collectAsState(initial = null)
  Text(connection?.handle?.value ?: "")
}

@Preview(showSystemUi = true)
@Composable
fun ConnectionPreview() {
  val navHostController = rememberNavController()
  val viewModel: ConnectionViewModel = viewModel()
  Connection(navHostController = navHostController, viewModel = viewModel, connectionId = UUID.randomUUID()) {}
}