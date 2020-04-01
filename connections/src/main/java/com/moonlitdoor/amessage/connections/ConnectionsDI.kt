package com.moonlitdoor.amessage.connections

import android.content.Context
import com.moonlitdoor.amessage.domain.DomainDI
import com.moonlitdoor.amessage.experiments.ExperimentsDI
import com.moonlitdoor.amessage.handle.HandleDI
import dagger.Component
import dagger.Module
import javax.inject.Scope

@Component(
  modules = [ConnectionsDI.ConnectionsModule::class],
  dependencies = [
    DomainDI::class,
    HandleDI::class,
    ExperimentsDI::class
  ]
)
@ConnectionsDI.ConnectionsScope
interface ConnectionsDI {

  fun inject(fragment: ConnectionsFragment)

  @Scope
  @kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
  annotation class ConnectionsScope

  @Module
  class ConnectionsModule

  @Component.Factory
  interface Factory {
    fun create(domainDI: DomainDI, handleDI: HandleDI, experimentsDI: ExperimentsDI): ConnectionsDI
  }

  companion object {

    private var component: ConnectionsDI? = null

    @Synchronized
    fun init(context: Context): ConnectionsDI = component ?: DaggerConnectionsDI.factory().create(
      handleDI = HandleDI.init(context),
      domainDI = DomainDI.init(context),
      experimentsDI = ExperimentsDI.init(context)
    ).also {
      component = it
    }

    @Synchronized
    fun get(): ConnectionsDI = component ?: run { throw Exception("Not Initialized") }

  }

}
