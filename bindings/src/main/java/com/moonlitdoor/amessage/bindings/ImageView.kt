package com.moonlitdoor.amessage.bindings

import android.graphics.Bitmap
import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("src")
fun ImageView.setImageViewResource(bitmap: Bitmap?) {
  this.setImageBitmap(bitmap)
}


