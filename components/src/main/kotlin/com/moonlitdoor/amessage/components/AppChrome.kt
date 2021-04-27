package com.moonlitdoor.amessage.components

import androidx.compose.material.FabPosition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

data class AppChrome(
  val title: String,
  val actionItems: List<ActionItem> = emptyList(),
  val showBottomBar: Boolean = false,
  val navigation: Navigation? = null,
  val fab: @Composable () -> Unit = {},
  val fabPosition: FabPosition = FabPosition.End,
  val modifier: Modifier = Modifier,
)
