package com.moonlitdoor.amessage.experiments.ui

import android.app.Activity
import dagger.Module
import dagger.Subcomponent

@Subcomponent(
  modules = [ExperimentsUiDI.ExperimentsUiModule::class]
)
interface ExperimentsUiDI {

  fun inject(fragment: ExperimentsFragment)

  interface ExperimentsUiDIProvider {
    fun provideExperimentsUiDI(): ExperimentsUiDI
  }

  @Module
  class ExperimentsUiModule

  @Subcomponent.Factory
  interface Factory {
    fun create(): ExperimentsUiDI
  }

  companion object {
    @Synchronized
    fun get(activity: Activity): ExperimentsUiDI = (activity.application as ExperimentsUiDIProvider).provideExperimentsUiDI()

  }

}
