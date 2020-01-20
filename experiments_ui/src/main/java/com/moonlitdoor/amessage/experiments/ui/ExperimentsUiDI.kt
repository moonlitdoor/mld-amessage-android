package com.moonlitdoor.amessage.experiments.ui

import android.content.Context
import com.moonlitdoor.amessage.experiments.ExperimentsDI
import dagger.Component
import dagger.Module

@Component(
  modules = [ExperimentsUiDI.ExperimentsUiModule::class]
)
interface ExperimentsUiDI {

  fun inject(fragment: ExperimentsFragment)

  @Module
  class ExperimentsUiModule

  companion object {

    private var component: ExperimentsUiDI? = null

    @Synchronized
    fun init(context: Context): ExperimentsUiDI = component ?: DaggerExperimentsUiDI.builder()
      .experimentsUiModule(ExperimentsDI.ExperimentsModule())
      .build().also {
        component = it
      }


    @Synchronized
    fun get(): ExperimentsUiDI = component ?: run { throw Exception("Not Initialized") }

  }

}
