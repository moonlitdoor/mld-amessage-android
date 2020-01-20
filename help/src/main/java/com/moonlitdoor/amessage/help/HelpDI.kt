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

  companion object {

    private var component: HelpDI? = null

    @Synchronized
    fun init(): HelpDI = component ?: DaggerHelpDI.builder()
      .aMessageDI(AMessageDI.get())
      .build().also {
        component = it
      }

    @Synchronized
    fun get(): HelpDI = component ?: run { throw Exception("Not Initialized") }

  }

}
