package com.moonlitdoor.amessage

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class BaseFragment : androidx.fragment.app.Fragment(), CoroutineScope {
  private val job = Job()

  override val coroutineContext: CoroutineContext
    get() = Dispatchers.Main + job

  override fun onDestroy() {
    job.cancel()
    super.onDestroy()
  }
}