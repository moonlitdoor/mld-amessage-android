package com.moonlitdoor.amessage.bindings

import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.widget.AppCompatSpinner
import androidx.databinding.BindingAdapter

@BindingAdapter("entries")
fun AppCompatSpinner.entries(entries: Array<String>) {
  this.adapter = ArrayAdapter<String>(this.context, android.R.layout.simple_spinner_item, entries)
}

@BindingAdapter("selected")
fun Spinner.setSelected(selected: String) {
  for (i in 0 until this.adapter.count) {
    if (this.adapter.getItem(i) == selected) {
      this.setSelection(i)
    }
  }
}

