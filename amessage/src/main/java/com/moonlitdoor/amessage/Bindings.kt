package com.moonlitdoor.amessage

import android.graphics.Bitmap
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ListAdapter

@BindingAdapter("src")
fun setImageViewResource(view: ImageView, bitmap: Bitmap?) {
  view.setImageBitmap(bitmap)
}

@Suppress("UNCHECKED_CAST")
@BindingAdapter("items")
fun <T, VH : androidx.recyclerview.widget.RecyclerView.ViewHolder> setItems(recyclerView: androidx.recyclerview.widget.RecyclerView, items: List<T>?) {
  items ?: return
  (recyclerView.adapter as? ListAdapter<T, VH>)?.submitList(items) ?: error("Must use a android.support.v7.recyclerview.extensions.ListAdapter for app:items")
}
