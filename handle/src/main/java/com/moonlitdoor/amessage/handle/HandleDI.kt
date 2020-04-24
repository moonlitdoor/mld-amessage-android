package com.moonlitdoor.amessage.handle

import dagger.Module
import dagger.Subcomponent

@Subcomponent(
  modules = [HandleDI.HandleModule::class]
)
interface HandleDI {

  fun handleViewModel(): HandleViewModel

  interface HandleDIProvider {
    fun provideHandleDI(): HandleDI
  }

  @Module
  class HandleModule

  @Subcomponent.Factory
  interface Factory {
    fun create(): HandleDI
  }

}
