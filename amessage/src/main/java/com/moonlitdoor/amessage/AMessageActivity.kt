package com.moonlitdoor.amessage

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.KEY_ROUTE
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.moonlitdoor.amessage.connections.Connections
import com.moonlitdoor.amessage.conversations.Conversations
import com.moonlitdoor.amessage.theme.AMessageTheme


class AMessageActivity : AppCompatActivity(), SharedPreferences.OnSharedPreferenceChangeListener {

  override fun onCreate(savedInstanceState: Bundle?) {
//    this.setTheme(
//      when (PreferenceManager.getDefaultSharedPreferences(this).getString(Constants.Keys.THEME, Constants.Keys.Defaults.THEME)) {
//        Constants.Values.Theme.DARK -> R.style.AmTheme_Dark
//        Constants.Values.Theme.MONSTER -> R.style.AmTheme_Monster
//        Constants.Values.Theme.DEEP -> R.style.AmTheme_Deep
//        Constants.Values.Theme.COOL -> R.style.AmTheme_Cool
//        else -> R.style.AmTheme
//      }
//    )
    super.onCreate(savedInstanceState)
    setContent {
      AMessageTheme {
        EdgeToEdgeContent {
          val navController = rememberNavController()
          Scaffold(
            topBar = {
              val currentScreenState by navController.currentBackStackEntryAsState()
              val currentScreen = Screen.fromRoute(currentScreenState?.arguments?.getString(KEY_ROUTE))
              TopAppBar(
                title = { Text(getString(currentScreen.resourceId)) },
                elevation = 120.dp
              )
            },
            bottomBar = {
              BottomNavigation() {
                val currentScreenState by navController.currentBackStackEntryAsState()
                val currentRoute = currentScreenState?.arguments?.getString(KEY_ROUTE)
                Screen.items.forEach { screen ->
                  BottomNavigationItem(
                    icon = { Icon(screen.icon, null) },
                    label = { Text(stringResource(screen.resourceId)) },
                    selected = currentRoute == screen.route,
                    onClick = {
                      navController.navigate(screen.route) {
                        popUpTo = navController.graph.startDestination
                        launchSingleTop = true
                      }
                    }
                  )
                }
              }
            },
            content = {
              NavHost(navController, startDestination = Screen.Conversations.route) {
                composable(Screen.Conversations.route) { Conversations(name = "Conversations") }
                composable(Screen.Connections.route) { Connections(name = "Connections") }
//            composable(Screen.Conversations.route) { Conversations(navController) }
//            composable(Screen.Connections.route) { Connections(navController) }
              }
            }
          )
        }
      }
    }
//    PreferenceManager.getDefaultSharedPreferences(this).registerOnSharedPreferenceChangeListener(this)
  }

//  override fun onSupportNavigateUp() = findNavController(this, R.id.fragment).navigateUp()

//  override fun onDestroy() {
//    super.onDestroy()
//    PreferenceManager.getDefaultSharedPreferences(this).unregisterOnSharedPreferenceChangeListener(this)
//  }

  override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
//    when (key) {
//      getString(R.string.preference_theme) -> recreate()
//    }
  }

  companion object {
    fun start(context: Context) = context.startActivity(Intent(context, AMessageActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK and Intent.FLAG_ACTIVITY_NEW_TASK))
  }
}
