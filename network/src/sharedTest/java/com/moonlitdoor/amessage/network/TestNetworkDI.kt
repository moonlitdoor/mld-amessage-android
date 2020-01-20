package com.moonlitdoor.amessage.network

import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Component(
  modules = [
    NetworkDI.NetworkModule::class,
    TestNetworkDI.TestBaseUrlModule::class
  ]
)
interface TestNetworkDI {

  fun inject(container: FirebaseClientContainer): FirebaseClientContainer

  @Module
  class TestBaseUrlModule(private val baseUrl: String) {

    @Provides
    @Named("base_url")
    fun providesFirebaseUrl(): String = baseUrl

  }

  companion object {

    private var component: TestNetworkDI? = null

    @Synchronized
    fun init(baseUrl: String): TestNetworkDI = component ?: DaggerTestNetworkDI.builder()
      .networkModule(NetworkDI.NetworkModule())
      .testBaseUrlModule(TestBaseUrlModule(baseUrl))
      .build().also {
        component = it
      }


    @Synchronized
    fun get(): TestNetworkDI = component ?: run { throw Exception("Not Initialized") }

  }

}