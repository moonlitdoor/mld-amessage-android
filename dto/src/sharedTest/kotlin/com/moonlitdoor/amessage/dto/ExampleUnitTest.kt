package com.moonlitdoor.amessage.dto

import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.Instant
import java.util.UUID

class ExampleUnitTest {
  @Test
  fun givenConnectionInvitePayload_whenSerialized_thenSerializationIsCorrect() {
    val payload = ConnectionInvitePayload(
      handle = "handle",
      token = "token",
      connectionId = UUID.fromString("36a5d5b2-c289-4541-a66d-4b49c6cd4f35"),
      associatedData = AssociatedDataDto(UUID.fromString("2e7f6b99-080a-4999-aae1-fd66635b7b22")),
      keys = KeysDto("keys"),
      scanned = Instant.MAX,
      confirmed = null,
    )
    assertEquals(
      """{"associatedData":{"value":"2e7f6b99-080a-4999-aae1-fd66635b7b22"},"connectionId":
        |"36a5d5b2-c289-4541-a66d-4b49c6cd4f35","handle":"handle","keys":{"value":"keys"},"scanned":"+1000000000-12-31T23:59:59.999999999Z","token":"token"}""".trimMargin(),
      payload.toString()
    )
  }

  @Test
  fun givenConnectionConfirmationPayload_whenSerialized_thenSerializationIsCorrect() {
    val payload = ConnectionConfirmationPayload(
      confirmed = null,
    )
    assertEquals("{}", payload.toString())
  }
}
