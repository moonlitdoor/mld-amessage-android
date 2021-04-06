package com.moonlitdoor.amessage.components

import androidx.compose.ui.graphics.vector.ImageVector

data class ActionItem(
  val enabled: Boolean,
  val onClick: () -> Unit,
  val imageVector: ImageVector,
)