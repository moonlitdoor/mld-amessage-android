package com.moonlitdoor.amessage.windows

import android.app.Activity
import com.moonlitdoor.amessage.AMessageDI
import com.moonlitdoor.amessage.Provider
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

  @Component.Factory
  interface Factory {
    fun create(aMessageDI: AMessageDI): WindowsDI
  }

  companion object {

    @Synchronized
    fun get(activity: Activity): WindowsDI = DaggerWindowsDI.factory().create((activity.application as Provider).provideAMessageDI())

  }

}
