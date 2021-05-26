package com.moonlitdoor.amessage

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.moonlitdoor.amessage.routes.Routes
import com.moonlitdoor.amessage.theme.AMessageTheme
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class AMessageActivity : AppCompatActivity() {

  @ExperimentalAnimationApi
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      Timber.d("AMessageActivity Composable")
      AMessageTheme {
        EdgeToEdgeContent {
          val navController: NavHostController = rememberNavController()
          var isBottomBarVisible by remember { mutableStateOf(true) }
          val showBottomBar: (Boolean) -> Unit = { isBottomBarVisible = it }
          Scaffold(
            bottomBar = {
              AnimatedVisibility(visible = isBottomBarVisible) {
                BottomNavigation {
                  val navBackStackEntry by navController.currentBackStackEntryAsState()
                  val currentRoute = navBackStackEntry?.destination?.route
                  BottomNavigationItem(
                    icon = { Icon(painterResource(id = R.drawable.ic_baseline_chat_24), null) },
                    selected = Routes.Conversations.route == currentRoute,
                    onClick = {
                      navController.navigate(Routes.Conversations.route) {
                        launchSingleTop = true
                      }
                    }
                  )
                  BottomNavigationItem(
                    icon = { Icon(painterResource(id = R.drawable.ic_baseline_group_24), null) },
                    selected = Routes.Connections.route == currentRoute,
                    onClick = {
                      navController.navigate(Routes.Connections.route) {
                        launchSingleTop = true
                      }
                    }
                  )
                  BottomNavigationItem(
                    icon = { Icon(painterResource(id = R.drawable.ic_baseline_person_add_24), null) },
                    selected = Routes.Connect.route == currentRoute,
                    onClick = {
                      navController.navigate(Routes.Connect.route) {
                        launchSingleTop = true
                      }
                    }
                  )
                  BottomNavigationItem(
                    icon = { Icon(Icons.Filled.MoreVert, null) },
                    selected = Routes.More.route == currentRoute,
                    onClick = {
                      navController.navigate(Routes.More.route) {
                        launchSingleTop = true
                      }
                    }
                  )
                }
              }
            },
          ) {
            Navigation(navHostController = navController, showBottomBar = showBottomBar)
          }
        }
      }
    }
  }

  companion object {
    fun start(context: Context) = context.startActivity(Intent(context, AMessageActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK and Intent.FLAG_ACTIVITY_NEW_TASK))
  }
}
