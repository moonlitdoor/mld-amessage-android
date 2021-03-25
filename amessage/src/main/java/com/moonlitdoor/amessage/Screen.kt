package com.moonlitdoor.amessage

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChatBubble
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.ui.graphics.vector.ImageVector
import com.moonlitdoor.amessage.routes.Routes

sealed class Screen(
  val route: String,
  val icon: ImageVector,
  @StringRes val resourceId: Int,
  val showBottomBar: Boolean,
) {
  object Conversations : Screen(
    route = Routes.Conversations.route,
    icon = Icons.Filled.ChatBubble,
    resourceId = R.string.conversations_title,
    showBottomBar = true,
  )

  object Handle : Screen(
    route = Routes.Handle.route,
    icon = Icons.Filled.Person,
    resourceId = R.string.handle_title,
    showBottomBar = false,
  )

  object Connections : Screen(
    route = Routes.Connections.route,
    icon = Icons.Filled.Group,
    resourceId = R.string.connections_title,
    showBottomBar = true,
  )

  object Connect : Screen(
    route = Routes.Connect.route,
    icon = Icons.Filled.PersonAdd,
    resourceId = R.string.connect_title,
    showBottomBar = true,
  )

  object More : Screen(
    route = Routes.More.route,
    icon = Icons.Filled.MoreVert,
    resourceId = R.string.connect_title,
    showBottomBar = true,
  )

  companion object {
    val items = listOf(
      Conversations,
      Connections,
      Connect,
    )

    fun fromRoute(route: String?): Screen = when (route) {
      Conversations.route -> Conversations
      Connections.route -> Connections
      Connect.route -> Connect
      else -> Conversations
    }

  }

}