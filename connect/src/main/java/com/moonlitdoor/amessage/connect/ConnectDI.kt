package com.moonlitdoor.amessage.connect

import android.app.Activity
import dagger.Module
import dagger.Subcomponent

@Subcomponent(
  modules = [ConnectDI.ConnectModule::class]
)
interface ConnectDI {

  fun inject(fragment: PendingFragment)
  fun inject(fragment: QrFragment)
  fun inject(fragment: ScanFragment)
  fun inject(fragment: InvitedFragment)

  interface ConnectDIProvider {
    fun provideConnectDI(): ConnectDI
  }

  @Module
  class ConnectModule

  @Subcomponent.Factory
  interface Factory {
    fun create(): ConnectDI
  }

  companion object {

    @Synchronized
    fun get(activity: Activity): ConnectDI = (activity.application as ConnectDIProvider).provideConnectDI()

  }

}
