package com.moonlitdoor.amessage.feedback

import com.moonlitdoor.amessage.AMessageDI
import dagger.Component
import dagger.Module
import javax.inject.Scope

@Component(
  modules = [FeedbackDI.FeedbackModule::class],
  dependencies = [AMessageDI::class]
)
@FeedbackDI.FeedbackScope
interface FeedbackDI {

  fun inject(fragment: FeedbackFragment)

  @Scope
  @kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
  annotation class FeedbackScope

  @Module
  open class FeedbackModule

  companion object {

    private var component: FeedbackDI? = null

    @Synchronized
    fun init(): FeedbackDI = component ?: DaggerFeedbackDI.builder()
      .aMessageDI(AMessageDI.get())
      .build().also {
        component = it
      }

    @Synchronized
    fun get(): FeedbackDI = component ?: run { throw Exception("Not Initialized") }

  }

}
