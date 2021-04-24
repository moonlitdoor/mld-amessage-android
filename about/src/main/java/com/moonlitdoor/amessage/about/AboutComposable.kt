package com.moonlitdoor.amessage.about

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.moonlitdoor.amessage.components.AppChrome
import com.moonlitdoor.amessage.components.Navigation

@Composable
fun About(navHostController: NavHostController, setAppChrome: (appChrome: AppChrome) -> Unit) {

  setAppChrome(
    AppChrome(
      title = stringResource(id = R.string.about_title),
      showBottomBar = false,
      navigation = Navigation { navHostController.popBackStack() }
    )
  )
}

@Preview(showBackground = true)
@Composable
fun AboutPreview() {
  MaterialTheme {
    val navHostController = rememberNavController()
    About(navHostController) {}
  }
}
