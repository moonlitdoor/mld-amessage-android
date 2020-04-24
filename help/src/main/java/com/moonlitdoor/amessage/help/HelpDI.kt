package com.moonlitdoor.amessage.help

import com.moonlitdoor.amessage.AMessageDI
import dagger.Component
import dagger.Module

@Component(
  modules = [HelpDI.HelpModule::class],
  dependencies = [AMessageDI::class]
)
interface HelpDI {

  fun inject(fragment: HelpFragment)

  @Module
  open class HelpModule

  @Component.Factory
  interface Factory {
    fun create(aMessageDI: AMessageDI): HelpDI
  }

  companion object {

    private var component: HelpDI? = null

    @Synchronized
    fun init(): HelpDI = component ?: DaggerHelpDI.factory().create(
      aMessageDI = AMessageDI.get()
    ).also {
      component = it
    }

    @Synchronized
    fun get(): HelpDI = component ?: run { throw Exception("Not Initialized") }

  }

}
