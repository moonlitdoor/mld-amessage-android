package com.moonlitdoor.amessage.more

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.moonlitdoor.amessage.components.AppChrome

@Composable
fun More(navHostController: NavHostController, setAppChrome: (appChrome: AppChrome) -> Unit) {

  setAppChrome(
    AppChrome(
      title = stringResource(id = R.string.more_title),
      showBottomBar = true
    )
  )

}

@Preview(showBackground = true)
@Composable
fun MorePreview() {
  MaterialTheme {
    val navHostController = rememberNavController()
    More(navHostController) {}
  }

}
