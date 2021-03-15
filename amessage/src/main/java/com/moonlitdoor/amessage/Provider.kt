package com.moonlitdoor.amessage

//import com.moonlitdoor.amessage.connect.ConnectDI
//import com.moonlitdoor.amessage.connections.ConnectionsDI
//import com.moonlitdoor.amessage.conversations.ConversationsDI
//import com.moonlitdoor.amessage.experiments.ui.ExperimentsUiDI
//import com.moonlitdoor.amessage.handle.HandleDI
//import com.moonlitdoor.amessage.push.PushDI
//import com.moonlitdoor.amessage.settings.SettingsDI

interface Provider/* :*/
//  ConnectDI.ConnectDIProvider,
//  ConnectionsDI.ConnectionsDIProvider,
//  ConversationsDI.ConversationsDIProvider,
//  ExperimentsUiDI.ExperimentsUiDIProvider,
//  HandleDI.HandleDIProvider,
//  PushDI.PushDIProvider,
//  SettingsDI.SettingsDIProvider
{

  fun provideAMessageDI(): AMessageDI

}
