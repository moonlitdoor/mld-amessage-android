package com.moonlitdoor.amessage.components

data class AppChrome(
  val title: String,
  val actionItems: List<ActionItem> = emptyList(),
  val showBottomBar: Boolean = false,
)
