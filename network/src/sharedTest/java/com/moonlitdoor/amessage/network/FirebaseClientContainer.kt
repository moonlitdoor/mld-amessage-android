package com.moonlitdoor.amessage.network

import com.moonlitdoor.amessage.network.client.FirebaseClient
import javax.inject.Inject

class FirebaseClientContainer {

  @Inject
  lateinit var client: FirebaseClient
}
