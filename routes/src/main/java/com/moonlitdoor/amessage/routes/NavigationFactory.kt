package com.moonlitdoor.amessage.routes

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

interface NavigationFactory {
  fun create(builder: NavGraphBuilder, navHostController: NavHostController, showBottomBar: (Boolean) -> Unit)
}
