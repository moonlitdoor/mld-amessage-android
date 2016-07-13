package com.moonlitdoor.amessage.util

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas

object UiUtils {

  fun dpToPx(dp: Int): Int {
    return (dp * Resources.getSystem().displayMetrics.density).toInt()
  }

  fun pxToDp(px: Int): Int {
    return (px / Resources.getSystem().displayMetrics.density).toInt()
  }

  fun getBitmap(context: Context, drawableRes: Int): Bitmap? {
    var bitmap: Bitmap? = null
    val drawable = context.getDrawable(drawableRes)
    if (drawable != null) {
      val canvas = Canvas()
      bitmap = Bitmap.createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
      canvas.setBitmap(bitmap)
      drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
      drawable.draw(canvas)
    }
    return bitmap
  }
}
