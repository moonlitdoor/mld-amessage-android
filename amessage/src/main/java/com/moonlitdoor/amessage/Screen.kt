package com.moonlitdoor.amessage

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(
  val route: String,
  val icon: ImageVector,
  @StringRes val resourceId: Int,
) {
  object Conversations : Screen(
    route = "profile",
    icon = Icons.Filled.Favorite,
    resourceId = R.string.generic_ok,
  )

  object Connections : Screen(
    route = "friends-list",
    icon = Icons.Filled.Email,
    resourceId = R.string.generic_cancel,
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