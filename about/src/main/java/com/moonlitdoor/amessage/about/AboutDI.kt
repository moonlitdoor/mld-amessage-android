package com.moonlitdoor.amessage.about

import com.moonlitdoor.amessage.AMessageDI
import dagger.Component
import dagger.Module
import javax.inject.Scope

@Component(
  modules = [AboutDI.AboutModule::class],
  dependencies = [AMessageDI::class]
)
@AboutDI.AboutScope
interface AboutDI {

  fun inject(fragment: AboutFragment)

  @Scope
  @kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
  annotation class AboutScope

  @Module
  open class AboutModule

  @Component.Factory
  interface Factory {
    fun create(aMessageDI: AMessageDI): AboutDI
  }

  companion object {

    private var component: AboutDI? = null

    @Synchronized
    fun init(): AboutDI = component ?: DaggerAboutDI.factory().create(
      aMessageDI = AMessageDI.get()
    ).also {
      component = it
    }

    @Synchronized
    fun get(): AboutDI = component ?: run { throw Exception("Not Initialized") }

  }

}
