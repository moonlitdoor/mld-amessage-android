package com.moonlitdoor.amessage.about

import com.moonlitdoor.amessage.AMessageDI
import dagger.Component
import dagger.Module

@Component(
  modules = [AboutDI.AboutModule::class],
  dependencies = [AMessageDI::class]
)
interface AboutDI {

  fun inject(fragment: AboutFragment)

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
