package com.moonlitdoor.amessage.feedback

import android.app.Activity
import com.moonlitdoor.amessage.AMessageDI
import com.moonlitdoor.amessage.Provider
import dagger.Component
import dagger.Module

@Component(
  modules = [FeedbackDI.FeedbackModule::class],
  dependencies = [AMessageDI::class]
)
interface FeedbackDI {

  fun inject(fragment: FeedbackFragment)

  @Module
  open class FeedbackModule

  @Component.Factory
  interface Factory {
    fun create(aMessageDI: AMessageDI): FeedbackDI
  }

  companion object {

    @Synchronized
    fun get(activity: Activity): FeedbackDI = DaggerFeedbackDI.factory().create((activity.application as Provider).provideAMessageDI())

  }

}
