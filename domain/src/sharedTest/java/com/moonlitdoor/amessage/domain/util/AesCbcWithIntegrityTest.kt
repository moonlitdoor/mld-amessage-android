package com.moonlitdoor.amessage.domain.util

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AesCbcWithIntegrityTest {

  @Test
  fun test() {
    val password = "password"
    val salt = "salt"
    val data = "This is a test string"
    val encrypted = AesCbcWithIntegrity.encrypt(data, password, salt)
    assertNotEquals(data, encrypted)
    val decrypted = AesCbcWithIntegrity.decrypt(encrypted, password, salt)
    assertEquals(data, decrypted)
  }

}