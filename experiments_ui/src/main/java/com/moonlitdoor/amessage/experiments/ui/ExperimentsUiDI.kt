package com.moonlitdoor.amessage.experiments.ui

import dagger.Component
import dagger.Module

@Component(
  modules = [ExperimentsUiDI.ExperimentsUiModule::class]
)
interface ExperimentsUiDI {

  fun inject(fragment: ExperimentsFragment)

  @Module
  class ExperimentsUiModule

  @Component.Factory
  interface Factory {
    fun create(): ExperimentsUiDI
  }

  companion object {

    private var component: ExperimentsUiDI? = null

    @Synchronized
    fun init(): ExperimentsUiDI = component ?: DaggerExperimentsUiDI.factory().create()
      .also {
        component = it
      }

    @Synchronized
    fun get(): ExperimentsUiDI = component ?: run { throw Exception("Not Initialized") }

  }

}
