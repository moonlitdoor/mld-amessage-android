package com.moonlitdoor.amessage.network.json

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.gson.Gson
import com.moonlitdoor.amessage.dto.AssociatedDataDto
import com.moonlitdoor.amessage.dto.ConnectionDto
import com.moonlitdoor.amessage.dto.ConnectionInvitePayload
import com.moonlitdoor.amessage.dto.FirebaseMessageDto
import com.moonlitdoor.amessage.dto.KeysDto
import com.moonlitdoor.amessage.network.StaticKeys
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import java.time.Instant
import java.util.UUID

@RunWith(AndroidJUnit4::class)
class FirebaseMessageTest {

  private val gson = Gson()

  @Test
  fun testConnectionInvitePayloadSerialization() {
    val payload = ConnectionInvitePayload("eldnah", "nekot", UUID.randomUUID(), AssociatedDataDto(UUID.randomUUID()), KeysDto(StaticKeys.value), Instant.now())
    val payloadAsJson: String = payload.toString()
    val payloadInflated = ConnectionInvitePayload.inflate(payloadAsJson)
    assertEquals(payload, payloadInflated)
  }

  @Test
  fun testConnectionInvitePayloadMessageSerialization() {
    val payload = ConnectionInvitePayload("eldnah", "nekot", UUID.randomUUID(), AssociatedDataDto(UUID.randomUUID()), KeysDto(StaticKeys.value), Instant.now())
    val connection = ConnectionDto(0, UUID.randomUUID(), "token", "handle", AssociatedDataDto(UUID.randomUUID()), KeysDto(StaticKeys.value))
    val message = FirebaseMessageDto(payload, connection)
    val json = gson.toJson(message)
    gson.fromJson(json, FirebaseMessageDto::class.java)
  }
}
