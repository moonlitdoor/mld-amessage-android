package com.moonlitdoor.amessage.routes

import androidx.navigation.NavType
import androidx.navigation.compose.NamedNavArgument
import androidx.navigation.compose.navArgument
import java.util.UUID

sealed class Routes(val route: String, val arguments: List<NamedNavArgument> = emptyList()) {
  object Conversations : Routes("conversations")
  object Connections : Routes("connections")
  class Connection(connectionId: UUID) : Routes("${Arguments.ROUTE}$connectionId") {

    object Arguments {
      const val ROUTE: String = "connections/"
      const val CONNECTION_ID: String = "connectionId"
    }

    companion object : Routes(
      route = "${Arguments.ROUTE}{${Arguments.CONNECTION_ID}}",
      arguments = listOf(navArgument(Arguments.CONNECTION_ID) { type = NavType.StringType })
    )
  }

  object FAQ : Routes("faq")
  object WhatsNew : Routes("whatsNew")
  object Connect : Routes("connect")
  object More : Routes("more")
  object About : Routes("about")
  object Handle : Routes("handle")
  object Help : Routes("help")
  object Windows : Routes("windows")
  object Settings : Routes("settings")
  object EmployeeSettings : Routes("employeeSettings")
  object DeveloperSettings : Routes("developerSettings")
  object Experiments : Routes("experiments")
  object Feedback : Routes("feedback")
}
