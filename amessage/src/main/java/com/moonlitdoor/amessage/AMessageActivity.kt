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
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.KEY_ROUTE
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.moonlitdoor.amessage.components.AppChrome
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
          var currentAppChrome by remember {
            mutableStateOf(
              AppChrome(
                title = getString(R.string.app_name),
                showBottomBar = true,
              )
            )
          }
          val setAppChrome: (appChrome: AppChrome) -> Unit = { currentAppChrome = it }
          Scaffold(
            topBar = {
              TopAppBar(
                title = {
                  Text(currentAppChrome.title)
                },
                navigationIcon = currentAppChrome.navigation?.let {
                  {
                    IconButton(onClick = it.onClick) {
                      Icon(
                        imageVector = it.imageVector,
                        contentDescription = stringResource(R.string.app_name)
                      )
                    }
                  }
                },
                elevation = 12.dp,
                actions = {
                  currentAppChrome.actionItems.forEach {
                    IconButton(
                      enabled = it.enabled,
                      onClick = it.onClick
                    ) {
                      Icon(
                        imageVector = it.imageVector,
                        contentDescription = stringResource(R.string.app_name)
                      )
                    }
                  }
                },
                modifier = currentAppChrome.modifier
              )
            },
            floatingActionButtonPosition = currentAppChrome.fabPosition,
            floatingActionButton = currentAppChrome.fab,
            bottomBar = {
              AnimatedVisibility(visible = currentAppChrome.showBottomBar) {
                BottomNavigation {
                  val currentState by navController.currentBackStackEntryAsState()
                  val currentRoute = currentState?.arguments?.getString(KEY_ROUTE)
                  BottomNavigationItem(
                    icon = { Icon(painterResource(id = R.drawable.ic_baseline_chat_24), null) },
                    selected = Routes.Conversations.route == currentRoute,
                    onClick = {
                      navController.navigate(Routes.Conversations.route) {
                        popUpTo = navController.graph.startDestination
                        launchSingleTop = true
                      }
                    }
                  )
                  BottomNavigationItem(
                    icon = { Icon(painterResource(id = R.drawable.ic_baseline_group_24), null) },
                    selected = Routes.Connections.route == currentRoute,
                    onClick = {
                      navController.navigate(Routes.Connections.route) {
                        popUpTo = navController.graph.startDestination
                        launchSingleTop = true
                      }
                    }
                  )
                  BottomNavigationItem(
                    icon = { Icon(painterResource(id = R.drawable.ic_baseline_person_add_24), null) },
                    selected = Routes.Connect.route == currentRoute,
                    onClick = {
                      navController.navigate(Routes.Connect.route) {
                        popUpTo = navController.graph.startDestination
                        launchSingleTop = true
                      }
                    }
                  )
                  BottomNavigationItem(
                    icon = { Icon(Icons.Filled.MoreVert, null) },
                    selected = Routes.More.route == currentRoute,
                    onClick = {
                      navController.navigate(Routes.More.route) {
                        popUpTo = navController.graph.startDestination
                        launchSingleTop = true
                      }
                    }
                  )
                }
              }
            },
            content = { Navigation(navHostController = navController, setAppChrome = setAppChrome) }
          )
        }
      }
    }
  }

  companion object {
    fun start(context: Context) = context.startActivity(Intent(context, AMessageActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK and Intent.FLAG_ACTIVITY_NEW_TASK))
  }
}
