package com.moonlitdoor.amessage.windows

import com.moonlitdoor.amessage.AMessageDI
import dagger.Component
import dagger.Module

@Component(
  modules = [WindowsDI.WindowsModule::class],
  dependencies = [AMessageDI::class]
)
interface WindowsDI {

  fun inject(fragment: WindowsFragment)

  @Module
  open class WindowsModule

  companion object {

    private var component: WindowsDI? = null

    @Synchronized
    fun init(): WindowsDI = component ?: DaggerWindowsDI.builder()
      .aMessageDI(AMessageDI.get())
      .build().also {
        component = it
      }

    @Synchronized
    fun get(): WindowsDI = component ?: run { throw Exception("Not Initialized") }

  }

}
