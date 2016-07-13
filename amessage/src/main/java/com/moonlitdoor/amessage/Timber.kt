package com.moonlitdoor.amessage

import android.util.Log
import com.crashlytics.android.Crashlytics
import timber.log.Timber

fun initTimber(isDebug: Boolean) = if (isDebug) Timber.plant(DecoratedDebugTree) else Timber.plant(DecoratedCrashReportingTree)

private object DecoratedDebugTree : Timber.DebugTree() {
  override fun log(priority: Int, tag: String?, message: String, t: Throwable?) = super.log(priority, tag, Decorator.decorate(message), t)
}

private object DecoratedCrashReportingTree : Timber.Tree() {
  override fun isLoggable(tag: String?, priority: Int): Boolean = priority >= Log.INFO
  override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
    if (priority < Log.INFO) return
    Crashlytics.log(priority, tag, Decorator.decorate(message))
    if (t != null) {
      Crashlytics.logException(t)
    }
  }
}

private object Decorator {
  fun decorate(msg: String): String = logThread(msg)
  private fun logThread(msg: String): String = "[thread=${Thread.currentThread().name}] $msg"
}