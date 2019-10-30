package com.moonlitdoor.amessage.extensions

import android.view.View
import android.widget.FrameLayout
import androidx.compose.Composable
import androidx.ui.core.setContent


fun androidx.fragment.app.Fragment.setComposable(content: @Composable() () -> Unit): View? =
  context?.let {
    FrameLayout(it).apply {
      layoutParams = android.view.ViewGroup.LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT, android.view.ViewGroup.LayoutParams.MATCH_PARENT)
      setContent(content)
    }
  }