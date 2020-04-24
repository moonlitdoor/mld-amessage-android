package com.moonlitdoor.amessage.push

import android.app.Service
import dagger.Module
import dagger.Subcomponent

@Subcomponent(modules = [PushDI.PushModule::class])
interface PushDI {

  fun inject(service: FirebaseMessagingService)

  interface PushDIProvider {
    fun providePushDI(): PushDI
  }

  @Module
  class PushModule

  @Subcomponent.Factory
  interface Factory {
    fun create(): PushDI
  }

  companion object {

    @Synchronized
    fun get(service: Service?): PushDI = (service?.application as PushDIProvider).providePushDI()

  }

}
