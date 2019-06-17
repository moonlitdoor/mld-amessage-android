package com.moonlitdoor.amessage.experiments

import org.koin.dsl.module

val experimentsDi = listOf(module {
  single { FirebaseRemoteConfigLive.get() as FirebaseRemoteConfigWrapper }
})