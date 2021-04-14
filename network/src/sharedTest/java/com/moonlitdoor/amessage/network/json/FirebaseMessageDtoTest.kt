package com.moonlitdoor.amessage.network.json

import com.moonlitdoor.amessage.dto.AssociatedDataDto
import com.moonlitdoor.amessage.dto.ConnectionInvitePayload
import com.moonlitdoor.amessage.dto.KeysDto
import com.moonlitdoor.amessage.network.StaticKeys
import org.junit.Test
import java.util.*

class FirebaseMessageDtoTest {

  @Test
  fun test() {
    ConnectionInvitePayload("handle", "token", UUID.randomUUID(), AssociatedDataDto(UUID.randomUUID()), KeysDto(StaticKeys.value))
  }

}