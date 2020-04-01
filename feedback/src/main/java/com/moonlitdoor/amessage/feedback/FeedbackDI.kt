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

  @Component.Factory
  interface Factory {
    fun create(aMessageDI: AMessageDI): FeedbackDI
  }

  companion object {

    private var component: FeedbackDI? = null

    @Synchronized
    fun init(): FeedbackDI = component ?: DaggerFeedbackDI.factory().create(
      aMessageDI = AMessageDI.get()
    ).also {
      component = it
    }

    @Synchronized
    fun get(): FeedbackDI = component ?: run { throw Exception("Not Initialized") }

  }

}
