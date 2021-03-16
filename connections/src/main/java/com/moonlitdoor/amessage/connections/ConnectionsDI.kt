//package com.moonlitdoor.amessage.connections
//
//import android.app.Activity
//import dagger.Module
//import dagger.Subcomponent
//
//@Subcomponent(modules = [ConnectionsDI.ConnectionsModule::class])
//interface ConnectionsDI {
//
//  fun inject(fragment: ConnectionsFragment)
//
//  interface ConnectionsDIProvider {
//    fun provideConnectionsDI(): ConnectionsDI
//  }
//
//  @Module
//  class ConnectionsModule
//
//  @Subcomponent.Factory
//  interface Factory {
//    fun create(): ConnectionsDI
//  }
//
//  companion object {
//
//    @Synchronized
//    fun get(activity: Activity): ConnectionsDI = (activity.application as ConnectionsDIProvider).provideConnectionsDI()
//
//  }
//
//}
