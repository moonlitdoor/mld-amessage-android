package com.moonlitdoor.amessage.components

import androidx.compose.ui.Modifier

data class AppChrome(
  val title: String,
  val actionItems: List<ActionItem> = emptyList(),
  val showBottomBar: Boolean = false,
  val navigation: Navigation? = null,
  val modifier: Modifier = Modifier,
)
