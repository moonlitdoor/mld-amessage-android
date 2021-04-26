package com.moonlitdoor.amessage.dto

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.moonlitdoor.amessage.dto.adapters.InstantAdapter
import org.junit.Assert.assertEquals
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import java.time.Instant

@RunWith(AndroidJUnit4::class)
class InstantAdapterTest {

  @Ignore("asdv")
  @Test
  fun givenAnInstant_whenSerializedAndDeserialized_thenTheValuesAreTheSame() {
    val instant = Instant.now()
    val out = InstantAdapter.toJson(instant)
    val result: Instant? = InstantAdapter.fromJson(out)
    assertEquals(result, instant)
  }

  @Test
  fun givenAnNullInstant_whenSerializedAndDeserialized_thenTheValuesAreTheSame() {
    val instant: Instant? = null
    val out = InstantAdapter.toJson(instant)
    val result: Instant? = InstantAdapter.fromJson(out)
    assertEquals(result, instant)
  }
}
