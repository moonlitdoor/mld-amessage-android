package com.moonlitdoor.amessage.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.ui.graphics.vector.ImageVector

data class Navigation(
  val imageVector: ImageVector = Icons.Filled.ArrowBack,
  val onClick: () -> Unit = {},
)
