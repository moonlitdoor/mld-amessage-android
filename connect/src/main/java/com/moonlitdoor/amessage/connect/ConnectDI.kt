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

  @Component.Factory
  interface Factory {
    fun create(domainDI: DomainDI): ConnectDI
  }

  companion object {

    private var component: ConnectDI? = null

    @Synchronized
    fun init(context: Context): ConnectDI = component ?: DaggerConnectDI.factory().create(
      domainDI = DomainDI.init(context)
    ).also {
      component = it
    }

    @Synchronized
    fun get(): ConnectDI = component ?: run { throw Exception("Not Initialized") }

  }

}
