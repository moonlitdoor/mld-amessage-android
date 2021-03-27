package com.moonlitdoor.amessage.root

import android.app.Application
import android.content.Context
import timber.log.Timber
import java.lang.ref.WeakReference

object Root {

  private lateinit var context: WeakReference<Application>

  fun init(application: Application) {
    context = WeakReference(application)
  }

  fun get(): Context = try {
    context.get()!!
  } catch (exception: Exception) {
    Timber.e(exception, "The Root WeakReference was null")
    throw  exception
  }

}
