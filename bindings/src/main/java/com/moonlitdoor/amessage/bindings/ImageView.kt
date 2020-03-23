package com.moonlitdoor.amessage.bindings

import android.graphics.Bitmap
import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("src")
fun ImageView.setImageViewResource(bitmap: Bitmap?) {
  this.setImageBitmap(bitmap)
}

@BindingAdapter(value = ["rotate", "rotationDuration"], requireAll = true)
fun ImageView.rotate(rotate: Float, rotationDuration: Long) {
  if (rotation != rotate) {
    this.animate().setDuration(rotationDuration).rotation(rotate).start()
  }
}


