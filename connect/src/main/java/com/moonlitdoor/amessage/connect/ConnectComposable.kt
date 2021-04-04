package com.moonlitdoor.amessage.connect

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.moonlitdoor.amessage.components.AppChrome

@Composable
fun Connect(navHostController: NavHostController, viewModel: ConnectViewModel, setAppChrome: (AppChrome) -> Unit) {
  setAppChrome(
    AppChrome(
      title = stringResource(id = R.string.connect_title),
      showBottomBar = true
    )
  )
  Text(text = "Hello, I am Connect!: $viewModel")
}

@Preview(showBackground = false)
@Composable
fun DefaultPreview() {
  MaterialTheme {
    val navHostController = rememberNavController()
    val viewModel: ConnectViewModel = viewModel()
    Connect(navHostController, viewModel) {}
  }
}
