package com.moonlitdoor.amessage.connections

import android.content.Context
import com.moonlitdoor.amessage.domain.DomainDI
import com.moonlitdoor.amessage.experiments.ExperimentsDI
import com.moonlitdoor.amessage.handle.HandleDI
import dagger.Component
import dagger.Module

@Component(
  modules = [ConnectionsDI.ConnectionsModule::class],
  dependencies = [
    DomainDI::class,
    HandleDI::class,
    ExperimentsDI::class
  ]
)
interface ConnectionsDI {

  fun inject(fragment: ConnectionsFragment)

  @Module
  class ConnectionsModule

  companion object {

    private var component: ConnectionsDI? = null

    @Synchronized
    fun init(context: Context): ConnectionsDI = component ?: DaggerConnectionsDI.builder()
      .handleDI(HandleDI.init(context))
      .domainDI(DomainDI.init(context))
      .experimentsDI(ExperimentsDI.init(context))
      .build().also {
        component = it
      }


    @Synchronized
    fun get(): ConnectionsDI = component ?: run { throw Exception("Not Initialized") }

  }

}
