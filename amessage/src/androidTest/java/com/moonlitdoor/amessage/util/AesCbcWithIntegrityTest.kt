package com.moonlitdoor.amessage.util

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

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