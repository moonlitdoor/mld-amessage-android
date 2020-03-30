package com.moonlitdoor.amessage.extensions

import android.view.View
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun View.setDebounceOnClickListener(duration: Long = 50, block: (v: View?) -> Unit) {
  setDebounceOnClickListener(duration, View.OnClickListener { v -> block.invoke(v) })
}

fun View.setDebounceOnClickListener(duration: Long = 50, listener: View.OnClickListener) {
  this.setOnClickListener(object : View.OnClickListener {

    private var clicked = false

    override fun onClick(v: View?) {
      if (!clicked) {
        clicked = true
        listener.onClick(v)
        findViewTreeLifecycleOwner()?.lifecycleScope?.launch {
          delay(duration)
          clicked = false
        }
      }
    }
  })

}
