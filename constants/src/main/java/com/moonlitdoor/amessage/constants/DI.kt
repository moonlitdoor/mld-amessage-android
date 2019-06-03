package com.moonlitdoor.amessage.constants

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val constantsDi = listOf(module {

  single { androidContext().resources }

})