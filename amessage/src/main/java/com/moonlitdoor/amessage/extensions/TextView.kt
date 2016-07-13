package com.moonlitdoor.amessage.extensions

import android.text.Editable
import android.text.TextWatcher
import android.widget.TextView

fun TextView.addTextChangedListener(
  after: (s: Editable?) -> Unit = {},
  before: (s: CharSequence?, start: Int, count: Int, after: Int) -> Unit = { _, _, _, _ -> },
  on: (s: CharSequence?, start: Int, before: Int, count: Int) -> Unit = { _, _, _, _ -> }
) {
  addTextChangedListener(object : TextWatcher {
    override fun afterTextChanged(st: Editable?) = after.invoke(st)
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = before(s, start, count, after)
    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = on(s, start, before, count)
  })
}

fun TextView.afterTextChanged(after: (s: Editable?) -> Unit) = addTextChangedListener(after = after)
fun TextView.beforeTextChanged(before: (s: CharSequence?, start: Int, count: Int, after: Int) -> Unit) = addTextChangedListener(before = before)
fun TextView.onTextChanged(on: (s: CharSequence?, start: Int, count: Int, after: Int) -> Unit) = addTextChangedListener(on = on)