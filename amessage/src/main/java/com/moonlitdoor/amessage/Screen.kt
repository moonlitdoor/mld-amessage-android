package com.moonlitdoor.amessage

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(
  val route: String,
  val icon: ImageVector,
  @StringRes val resourceId: Int,
) {
  object Conversations : Screen(
    route = "conversations",
    icon = Icons.Filled.Call,
    resourceId = R.string.conversations_title,
  )

  object Connections : Screen(
    route = "connections",
    icon = Icons.Filled.Person,
    resourceId = R.string.connections_title,
  )

  companion object {
    val items = listOf(
      Conversations,
      Connections,
    )

    fun fromRoute(route: String?): Screen = when (route) {
      Conversations.route -> Conversations
      Connections.route -> Connections
      else -> Conversations
    }

  }

}