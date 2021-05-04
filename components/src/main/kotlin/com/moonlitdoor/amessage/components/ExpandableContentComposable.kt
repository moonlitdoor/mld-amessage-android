package com.moonlitdoor.amessage.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

private const val FADE_IN_ANIMATION_DURATION = 600

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ExpandableContent(
  visible: Boolean = true,
  initialVisibility: Boolean = false,
  content: @Composable () -> Unit,
) {
  val enterFadeIn = remember {
    fadeIn(
      animationSpec = TweenSpec(
        durationMillis = FADE_IN_ANIMATION_DURATION,
        easing = FastOutLinearInEasing
      )
    )
  }
  val enterExpand = remember {
    expandVertically(animationSpec = tween(FADE_IN_ANIMATION_DURATION))
  }
  val exitFadeOut = remember {
    fadeOut(
      animationSpec = TweenSpec(
        durationMillis = FADE_IN_ANIMATION_DURATION,
        easing = LinearOutSlowInEasing
      )
    )
  }
  val exitCollapse = remember {
    shrinkVertically(animationSpec = tween(FADE_IN_ANIMATION_DURATION))
  }
  AnimatedVisibility(
    visible = visible,
    initiallyVisible = initialVisibility,
    enter = enterExpand + enterFadeIn,
    exit = exitCollapse + exitFadeOut,
    content = content
  )
}
