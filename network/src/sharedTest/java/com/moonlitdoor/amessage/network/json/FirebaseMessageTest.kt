package com.moonlitdoor.amessage.network.json

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.gson.Gson
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

@RunWith(AndroidJUnit4::class)
class FirebaseMessageTest {

  private val gson = Gson()

  @Test
  fun testConnectionInvitePayloadSerialization() {
    val payload = ConnectionInvitePayload("eldnah", "nekot", UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID())
    val payloadAsJson: String = payload.toString()
    val payloadInflated = ConnectionInvitePayload.inflate(payloadAsJson)
    assertEquals(payload, payloadInflated)
  }

  @Test
  fun testConnectionInvitePayloadMessageSerialization() {
    val payload = ConnectionInvitePayload("eldnah", "nekot", UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID())
    val connection = ConnectionJson(0, UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), "token", "handle")
    val message = FirebaseMessageJson(payload, connection)
    val json = gson.toJson(message)
    val messageJson = gson.fromJson(json, FirebaseMessageJson::class.java)

  }

}