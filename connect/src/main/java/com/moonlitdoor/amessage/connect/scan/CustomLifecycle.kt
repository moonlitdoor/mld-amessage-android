package com.moonlitdoor.amessage.connect.scan

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry

object CustomLifecycle : LifecycleOwner {

  private val lifecycleRegistry: LifecycleRegistry = LifecycleRegistry(this)

  fun onResume() {
    lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_CREATE)
    lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_START)
    lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
  }

  fun onPause() {
    lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_STOP)
  }

  override fun getLifecycle(): Lifecycle {
    return lifecycleRegistry
  }

}