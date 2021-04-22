package com.moonlitdoor.amessage.root

import android.content.Context
import timber.log.Timber
import java.lang.ref.WeakReference

object Root {

  private lateinit var reference: WeakReference<Context>

  fun init(context: Context) {
    reference = WeakReference(context)
  }

  fun get(): Context = try {
    reference.get()!!
  } catch (exception: Exception) {
    Timber.e(exception, "The Root WeakReference was null")
    throw  exception
  }

}
