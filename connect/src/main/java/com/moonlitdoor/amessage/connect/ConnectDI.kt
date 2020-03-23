package com.moonlitdoor.amessage.connect

import android.content.Context
import com.moonlitdoor.amessage.domain.DomainDI
import dagger.Component
import dagger.Module
import javax.inject.Scope

@Component(
  modules = [ConnectDI.ConnectModule::class],
  dependencies = [DomainDI::class]
)
@ConnectDI.ConnectScope
interface ConnectDI {

  fun inject(fragment: PendingFragment)
  fun inject(fragment: QrFragment)
  fun inject(fragment: ScanFragment)

  @Scope
  @kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
  annotation class ConnectScope

  @Module
  class ConnectModule

  companion object {

    private var component: ConnectDI? = null

    @Synchronized
    fun init(context: Context): ConnectDI = component ?: DaggerConnectDI.builder()
      .domainDI(DomainDI.init(context))
      .build().also {
        component = it
      }


    @Synchronized
    fun get(): ConnectDI = component ?: run { throw Exception("Not Initialized") }

  }

}
