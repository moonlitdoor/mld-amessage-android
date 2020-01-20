package com.moonlitdoor.amessage.constants

import android.content.Context
import android.content.res.Resources
import dagger.Component
import dagger.Module
import dagger.Provides

@Component(modules = [ConstantsDI.ConstantsModule::class])
interface ConstantsDI {

  fun inject(res: Constants.Res): Constants.Res

  @Module
  class ConstantsModule(private var context: Context) {

    @Provides
    fun provideResources(): Resources = context.resources

  }

  companion object {

    private var component: ConstantsDI? = null

    @Synchronized
    fun init(context: Context): ConstantsDI = component ?: DaggerConstantsDI.builder()
      .constantsModule(ConstantsModule(context))
      .build().also {
        component = it
      }

    @Synchronized
    fun get(): ConstantsDI = component ?: run { throw Exception("Not Initialized") }

  }

}
