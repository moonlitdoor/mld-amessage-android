package com.moonlitdoor.amessage.about

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.moonlitdoor.amessage.components.AppChrome

@Composable
fun Statistics(navHostController: NavHostController, setAppChrome: (appChrome: AppChrome) -> Unit) {

  setAppChrome(
    AppChrome(
      title = stringResource(id = R.string.more_title),
      showBottomBar = true
    )
  )

//  VERSION
//  BUILD DATE
//  Privacy Policy

}

@Preview(showBackground = true)
@Composable
fun StatisticsPreview() {
  MaterialTheme {
    val navHostController = rememberNavController()
    Statistics(navHostController) {}
  }

}
