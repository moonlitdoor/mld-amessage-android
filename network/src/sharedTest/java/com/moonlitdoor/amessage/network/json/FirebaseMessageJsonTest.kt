package com.moonlitdoor.amessage.network.json

import org.junit.Test
import java.util.*

class FirebaseMessageJsonTest {

  @Test
  fun test() {
    val payload = ConnectionInvitePayload("handle", "token", UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID())
//    println(payload)

  }

}