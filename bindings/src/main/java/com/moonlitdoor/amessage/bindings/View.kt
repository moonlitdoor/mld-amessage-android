package com.moonlitdoor.amessage.bindings

import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.Transformation
import androidx.databinding.BindingAdapter


@BindingAdapter(value = ["expand"])
fun View.expand(expand: Boolean) {
  val actualHeight: Int = this.measuredHeight
  val animation: Animation = if (expand) {
    measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    layoutParams.height = 0
    visibility = View.VISIBLE
    object : Animation() {
      override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
        layoutParams.height = if (interpolatedTime == 1f) ViewGroup.LayoutParams.WRAP_CONTENT else (actualHeight * interpolatedTime).toInt()
        requestLayout()
      }
    }
  } else {
    object : Animation() {
      override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
        if (interpolatedTime == 1f) {
          visibility = View.GONE
        } else {
          layoutParams.height = actualHeight - (actualHeight * interpolatedTime).toInt()
          requestLayout()
        }
      }
    }
  }
  animation.duration = (actualHeight / context.resources.displayMetrics.density).toLong()
  startAnimation(animation)
}
