package com.moonlitdoor.amessage.network.json

import com.moonlitdoor.amessage.dto.AssociatedDataDto
import com.moonlitdoor.amessage.dto.ConnectionInvitePayload
import com.moonlitdoor.amessage.dto.KeysDto
import com.moonlitdoor.amessage.network.StaticKeys
import org.junit.Test
import java.time.Instant
import java.util.UUID

class FirebaseMessageDtoTest {

  @Test
  fun test() {
    ConnectionInvitePayload(
      handle = "handle",
      token = "token",
      connectionId = UUID.randomUUID(),
      profileId = UUID.randomUUID(),
      associatedData = AssociatedDataDto(UUID.randomUUID()),
      keys = KeysDto(StaticKeys.value),
      scanned = Instant.now()
    )
  }
}
