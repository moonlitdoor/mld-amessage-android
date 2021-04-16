package com.moonlitdoor.amessage.more

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.moonlitdoor.amessage.components.AppChrome
import com.moonlitdoor.amessage.experiments.Experiments
import com.moonlitdoor.amessage.routes.Routes

@Composable
fun More(navHostController: NavHostController, setAppChrome: (appChrome: AppChrome) -> Unit) {

  setAppChrome(
    AppChrome(
      title = stringResource(id = R.string.more_title),
      showBottomBar = true
    )
  )
  Column(modifier = Modifier.fillMaxSize()) {
    if (Experiments.FEATURE_ABOUT.value.asBoolean) {
      MoreItem(R.string.about_title) { navHostController.navigate(Routes.About.route) }
    }
    if (Experiments.FEATURE_FAQ.value.asBoolean) {
      MoreItem(R.string.faq_title) { navHostController.navigate(Routes.FAQ.route) }
    }
    if (Experiments.FEATURE_WHATS_NEW.value.asBoolean) {
      MoreItem(R.string.whats_new_title) { navHostController.navigate(Routes.WhatsNew.route) }
    }
    if (Experiments.FEATURE_FEEDBACK.value.asBoolean) {
      MoreItem(R.string.feedback_title) { navHostController.navigate(Routes.Feedback.route) }
    }
    if (Experiments.FEATURE_HELP.value.asBoolean) {
      MoreItem(R.string.help_title) { navHostController.navigate(Routes.Help.route) }
    }
    if (Experiments.FEATURE_WINDOWS.value.asBoolean) {
      MoreItem(R.string.windows_title) { navHostController.navigate(Routes.Windows.route) }
    }
    if (Experiments.FEATURE_SETTINGS.value.asBoolean) {
      MoreItem(R.string.settings_title) { navHostController.navigate(Routes.Settings.route) }
    }
  }
}

@Preview(showBackground = true)
@Composable
fun MorePreview() {
  MaterialTheme {
    val navHostController = rememberNavController()
    More(navHostController) {}
  }

}
