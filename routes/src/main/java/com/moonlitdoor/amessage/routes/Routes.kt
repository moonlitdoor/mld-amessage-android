package com.moonlitdoor.amessage.routes

sealed class Routes(val route: String) {
  object Conversations : Routes("conversations")
  object Connections : Routes("connections")
  object Connect : Routes("connect")
}
