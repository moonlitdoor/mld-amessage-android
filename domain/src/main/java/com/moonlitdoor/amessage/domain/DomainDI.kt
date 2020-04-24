package com.moonlitdoor.amessage.domain

import com.moonlitdoor.amessage.database.DatabaseDI
import com.moonlitdoor.amessage.network.NetworkDI
import dagger.Module

interface DomainDI {

  @Module(
    includes = [
      DatabaseDI.DatabaseModule::class,
      NetworkDI.NetworkModule::class,
      NetworkDI.BaseUrlModule::class
    ]
  )
  class DomainModule


}
