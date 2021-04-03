package com.moonlitdoor.amessage.network.json

import com.moonlitdoor.amessage.dto.ConnectionInvitePayload
import org.junit.Test
import java.util.*

class FirebaseMessageJsonTest {

  @Test
  fun test() {
    ConnectionInvitePayload("handle", "token", UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID())
  }

}