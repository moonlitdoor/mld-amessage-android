package com.moonlitdoor.amessage.handle

import android.content.Context
import com.moonlitdoor.amessage.domain.DomainDI
import dagger.Component
import dagger.Module

@Component(
  modules = [HandleDI.HandleModule::class],
  dependencies = [DomainDI::class]
)
interface HandleDI {

  fun handleViewModel(): HandleViewModel

  @Module
  class HandleModule

  companion object {

    private var component: HandleDI? = null

    @Synchronized
    fun init(context: Context): HandleDI = component ?: DaggerHandleDI.builder()
      .domainDI(DomainDI.init(context))
      .build().also {
        component = it
      }


    @Synchronized
    fun get(): HandleDI = component ?: run { throw Exception("Not Initialized") }

  }

}
