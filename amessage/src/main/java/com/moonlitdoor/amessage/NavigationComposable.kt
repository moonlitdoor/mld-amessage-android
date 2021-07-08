package com.moonlitdoor.amessage

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.moonlitdoor.amessage.routes.NavigationFactory
import com.moonlitdoor.amessage.routes.Routes
import timber.log.Timber

@Composable
fun Navigation(navHostController: NavHostController, showBottomBar: (Boolean) -> Unit, navigationFactories: @JvmSuppressWildcards Set<NavigationFactory>) {
  Timber.d("Navigation Composable")
  NavHost(navHostController, startDestination = Routes.Conversations.route) {
    navigationFactories.forEach { factory ->
      factory.create(this, navHostController, showBottomBar)
    }
  }
}
