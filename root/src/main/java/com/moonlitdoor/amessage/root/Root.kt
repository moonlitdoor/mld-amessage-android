package com.moonlitdoor.amessage.root

import android.app.Application
import android.content.Context

object Root {

  private lateinit var context: Application

  fun init(application: Application) {
    context = application
  }

  fun get(): Context = context

}
