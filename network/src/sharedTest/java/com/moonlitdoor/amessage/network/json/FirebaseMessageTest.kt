package com.moonlitdoor.amessage.network.json

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.gson.GsonBuilder
import com.moonlitdoor.amessage.dto.AssociatedDataDto
import com.moonlitdoor.amessage.dto.ConnectionDto
import com.moonlitdoor.amessage.dto.ConnectionInvitePayload
import com.moonlitdoor.amessage.dto.FirebaseMessageDto
import com.moonlitdoor.amessage.dto.KeysDto
import com.moonlitdoor.amessage.dto.adapters.InstantAdapter
import com.moonlitdoor.amessage.network.StaticKeys
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import java.time.Instant
import java.util.UUID

@RunWith(AndroidJUnit4::class)
class FirebaseMessageTest {

  private val gson = GsonBuilder().registerTypeAdapter(Instant::class.java, InstantAdapter).create()

  @Test
  fun testConnectionInvitePayloadSerialization() {
    val payload = ConnectionInvitePayload(
      handle = "eldnah",
      token = "nekot",
      connectionId = UUID.randomUUID(),
      profileId = UUID.randomUUID(),
      associatedData = AssociatedDataDto(UUID.randomUUID()),
      keys = KeysDto(StaticKeys.value),
      scanned = Instant.now(),
      confirmed = Instant.now()
    )
    val payloadAsJson: String = payload.toString()
    val payloadInflated = ConnectionInvitePayload.inflate(payloadAsJson)
    assertEquals(payload, payloadInflated)
  }

  @Test
  fun testConnectionInvitePayloadMessageSerialization() {
    val payload = ConnectionInvitePayload(
      handle = "eldnah",
      token = "nekot",
      connectionId = UUID.randomUUID(),
      profileId = UUID.randomUUID(),
      associatedData = AssociatedDataDto(UUID.randomUUID()),
      keys = KeysDto(StaticKeys.value),
      scanned = Instant.now(),
      confirmed = Instant.now()
    )
    val connection = ConnectionDto(
      connectionId = UUID.randomUUID(),
      profileId = UUID.randomUUID(),
      token = "token",
      handle = "handle",
      associatedData = AssociatedDataDto(UUID.randomUUID()),
      keys = KeysDto(StaticKeys.value),
      scanned = Instant.now(),
      confirmed = Instant.now(),
    )
    val message = FirebaseMessageDto(payload, connection)
    val json = gson.toJson(message)
    gson.fromJson(json, FirebaseMessageDto::class.java)
  }
}
