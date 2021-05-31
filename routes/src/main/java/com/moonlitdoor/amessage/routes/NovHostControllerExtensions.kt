package com.moonlitdoor.amessage.routes

import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.Navigator
import androidx.navigation.navOptions

fun NavHostController.navigate(
  route: Routes,
  navOptions: NavOptions? = null,
  navigatorExtras: Navigator.Extras? = null
) = navigate(route.route, navOptions, navigatorExtras)

fun NavHostController.navigate(route: Routes, builder: NavOptionsBuilder.() -> Unit) {
  navigate(route, navOptions(builder))
}
