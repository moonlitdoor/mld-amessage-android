package com.moonlitdoor.amessage

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import android.preference.PreferenceManager

//import com.moonlitdoor.amessage.connect.ConnectDI
//import com.moonlitdoor.amessage.connections.ConnectionsDI
//import com.moonlitdoor.amessage.conversations.ConversationsDI
//import com.moonlitdoor.amessage.domain.DomainDI
//import com.moonlitdoor.amessage.domain.repository.FrequentlyAskedQuestionRepository
//import com.moonlitdoor.amessage.experiments.ui.ExperimentsUiDI
//import com.moonlitdoor.amessage.handle.HandleDI
//import com.moonlitdoor.amessage.push.PushDI
//import com.moonlitdoor.amessage.settings.SettingsDI
//import dagger.Component
//import dagger.Module
//import dagger.Provides


//@Component(
//  modules = [
//    AMessageDI.AMessageModule::class,
//    AMessageDI.SubcomponentsModule::class,
//    DomainDI.DomainModule::class
//  ]
//)
interface AMessageDI {

  fun inject(application: AMessageApplication)

  fun inject(service: DatabasePopulationService)

//  fun frequentlyAskedQuestionRepository(): FrequentlyAskedQuestionRepository

//  fun connectDIFactory(): ConnectDI.Factory

//  fun connectionsDIFactory(): ConnectionsDI.Factory

//  fun conversationsDIFactory(): ConversationsDI.Factory

//  fun experimentsUiDIFactory(): ExperimentsUiDI.Factory

//  fun handleDIFactory(): HandleDI.Factory

//  fun pushDIFactory(): PushDI.Factory

//  fun settingsDIFactory(): SettingsDI.Factory

//  @Module
//  class AMessageModule(private val context: Context) {
//
//    @Provides
//    fun providesContext(): Context = context
//
//    @Provides
//    fun provideResources(context: Context): Resources = context.resources
//
//    @Provides
//    fun providesSharedPreferences(context: Context): SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
//
//  }

  //  @Module(
//    subcomponents = [
//      ConnectDI::class,
//      ConnectionsDI::class,
//      ConversationsDI::class,
//      HandleDI::class,
//      PushDI::class,
//      SettingsDI::class
//    ]
//  )
  class SubcomponentsModule

//  @Component.Factory
//  interface Factory {
//    fun create(aMessageModule: AMessageModule): AMessageDI
//  }

  companion object : Provider {

    private var component: AMessageDI? = null

    override fun provideAMessageDI(): AMessageDI = get()

//    override fun provideConnectDI(): ConnectDI = provideAMessageDI().connectDIFactory().create()

//    override fun provideConnectionsDI(): ConnectionsDI = provideAMessageDI().connectionsDIFactory().create()

//    override fun provideConversationsDI(): ConversationsDI = provideAMessageDI().conversationsDIFactory().create()

//    override fun provideExperimentsUiDI(): ExperimentsUiDI = provideAMessageDI().experimentsUiDIFactory().create()

//    override fun provideHandleDI(): HandleDI = provideAMessageDI().handleDIFactory().create()

//    override fun providePushDI(): PushDI = provideAMessageDI().pushDIFactory().create()

//    override fun provideSettingsDI(): SettingsDI = provideAMessageDI().settingsDIFactory().create()

//    @Synchronized
//    fun init(context: Context): AMessageDI = component ?: DaggerAMessageDI.factory().create(AMessageModule(context)).also {
//      component = it
//    }

    @Synchronized
    fun get(): AMessageDI = component ?: run { throw Exception("Not Initialized") }

  }

}
