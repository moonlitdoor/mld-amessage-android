package com.moonlitdoor.amessage

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.moonlitdoor.amessage.routes.Routes

sealed class Screen(
  val route: String,
  val icon: ImageVector,
  @StringRes val resourceId: Int,
) {
  object Conversations : Screen(
    route = Routes.Conversations.route,
    icon = Icons.Filled.Call,
    resourceId = R.string.conversations_title,
  )

  object Connections : Screen(
    route = Routes.Connections.route,
    icon = Icons.Filled.Person,
    resourceId = R.string.connections_title,
  )

  object Connect : Screen(
    route = Routes.Connect.route,
    icon = Icons.Filled.Person,
    resourceId = R.string.connect_title,
  )

  companion object {
    val items = listOf(
      Conversations,
      Connections,
      Connect
    )

    fun fromRoute(route: String?): Screen = when (route) {
      Conversations.route -> Conversations
      Connections.route -> Connections
      Connect.route -> Connect
      else -> Conversations
    }

  }

}