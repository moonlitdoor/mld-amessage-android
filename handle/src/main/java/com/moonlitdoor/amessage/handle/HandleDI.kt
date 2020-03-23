package com.moonlitdoor.amessage.handle

import android.content.Context
import com.moonlitdoor.amessage.domain.DomainDI
import dagger.Component
import dagger.Module
import javax.inject.Scope

@Component(
  modules = [HandleDI.HandleModule::class],
  dependencies = [DomainDI::class]
)
@HandleDI.HandleScope
interface HandleDI {

  fun handleViewModel(): HandleViewModel

  @Scope
  @kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
  annotation class HandleScope

  @Module
  class HandleModule

  companion object {

    private var component: HandleDI? = null

    @Synchronized
    fun init(context: Context): HandleDI = component ?: DaggerHandleDI.builder()
      .domainDI(DomainDI.init(context))
      .build().also {
        component = it
      }


    @Synchronized
    fun get(): HandleDI = component ?: run { throw Exception("Not Initialized") }

  }

}
