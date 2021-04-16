package com.moonlitdoor.amessage.routes

import androidx.navigation.NavType
import androidx.navigation.compose.NamedNavArgument
import androidx.navigation.compose.navArgument

sealed class Routes(val route: String, val arguments: List<NamedNavArgument> = emptyList()) {
  object Conversations : Routes("conversations")
  object Connections : Routes("connections")
  class Connection(connectionId: Long) : Routes("${Arguments.ROUTE}$connectionId") {

    object Arguments {
      const val ROUTE: String = "connections/"
      const val CONNECTION_ID: String = "connectionId"
    }

    companion object : Routes(
      route = "${Arguments.ROUTE}{${Arguments.CONNECTION_ID}}",
      arguments = listOf(navArgument(Arguments.CONNECTION_ID) { type = NavType.LongType })
    )

  }

  object Connect : Routes("connect")
  object More : Routes("more")
  object About : Routes("about")
  object Handle : Routes("handle")
  object Feedback : Routes("feedback")
}
