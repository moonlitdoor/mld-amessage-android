package com.moonlitdoor.amessage

import android.util.Log
import com.google.firebase.crashlytics.FirebaseCrashlytics
import timber.log.Timber

object TimberInit {

  operator fun invoke(isDebug: Boolean) = if (isDebug) Timber.plant(DecoratedDebugTree) else Timber.plant(DecoratedCrashReportingTree)

  private object DecoratedDebugTree : Timber.DebugTree() {
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) = super.log(priority, tag, Decorator.decorate(message), t)
  }

  private object DecoratedCrashReportingTree : Timber.Tree() {

    private val crashlytics = FirebaseCrashlytics.getInstance().also {
      it.setUserId(UserId.value.toString())
      it.sendUnsentReports()
    }

    override fun isLoggable(tag: String?, priority: Int): Boolean = priority >= Log.INFO

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
      crashlytics.log(Decorator.decorate(message, tag))
      t?.let { crashlytics.recordException(it) }
    }
  }

  private object Decorator {
    fun decorate(msg: String, tag: String? = null): String = logTag(msg, tag)

    private fun logTag(msg: String, tag: String?): String = "${tag?.let { "$it: " } ?: ""}${logThread(msg)}"

    private fun logThread(msg: String): String = "[thread=${Thread.currentThread().name}] $msg"
  }
}
