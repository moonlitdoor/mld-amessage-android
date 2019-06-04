package com.moonlitdoor.amessage.bindings

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

@Suppress("UNCHECKED_CAST")
@BindingAdapter("items")
fun <T, VH : RecyclerView.ViewHolder> RecyclerView.bindItems(items: List<T>?) {
  items ?: return
  (this.adapter as? ListAdapter<T, VH>)?.submitList(items) ?: error("Must use a '${ListAdapter::class.java.canonicalName}' for app:items")
}