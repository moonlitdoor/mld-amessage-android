package com.moonlitdoor.amessage.encryption

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AesCbcWithIntegrityTest {

  @Test
  fun givenAString_whenItIsEncrypted_thenItDoesNotEqualTheString() {
    val password = "password"
    val salt = "salt"
    val data = "This is a test string"
    val encrypted = AesCbcWithIntegrity.encrypt(data, password, salt)
    assertNotEquals(data, encrypted)
  }

  @Test
  fun givenAString_whenItIsEncryptedTwiceWithTheSamePasswordAndSalt_thenTheTwoEncryptedStringsAreNotEqual() {
    val password = "password"
    val salt = "salt"
    val data = "This is a test string"
    val encrypted1 = AesCbcWithIntegrity.encrypt(data, password, salt)
    val encrypted2 = AesCbcWithIntegrity.encrypt(data, password, salt)
    assertNotEquals(encrypted1, encrypted2)
  }

  @Test
  fun givenAString_whenItIsEncryptedTwiceWithDifferentPasswordsAndTheSameSalt_thenTheTwoEncryptedStringsAreNotEqual() {
    val password1 = "password"
    val password2 = "drowssap"
    val salt = "salt"
    val data = "This is a test string"
    val encrypted1 = AesCbcWithIntegrity.encrypt(data, password1, salt)
    val encrypted2 = AesCbcWithIntegrity.encrypt(data, password2, salt)
    assertNotEquals(encrypted1, encrypted2)
  }

  @Test
  fun givenAString_whenItIsEncryptedTwiceWithTheSamePasswordAndDifferentSalts_thenTheTwoEncryptedStringsAreNotEqual() {
    val password = "password"
    val salt1 = "salt"
    val salt2 = "tlas"
    val data = "This is a test string"
    val encrypted1 = AesCbcWithIntegrity.encrypt(data, password, salt1)
    val encrypted2 = AesCbcWithIntegrity.encrypt(data, password, salt2)
    assertNotEquals(encrypted1, encrypted2)
  }

  @Test
  fun givenAString_whenItIsEncryptedTwiceWithDifferentPasswordsAndDifferentSalts_thenTheTwoEncryptedStringsAreNotEqual() {
    val password1 = "password"
    val password2 = "drowssap"
    val salt1 = "salt"
    val salt2 = "tlas"
    val data = "This is a test string"
    val encrypted1 = AesCbcWithIntegrity.encrypt(data, password1, salt1)
    val encrypted2 = AesCbcWithIntegrity.encrypt(data, password2, salt2)
    assertNotEquals(encrypted1, encrypted2)
  }

  @Test
  fun givenAString_whenItIsEncryptedAndDecryptedWithDifferentPasswordsAndDifferentSalts_thenTheTwoDecryptedStringsAreEqual() {
    val password1 = "password"
    val password2 = "drowssap"
    val salt1 = "salt"
    val salt2 = "tlas"
    val data = "This is a test string"
    val encrypted1 = AesCbcWithIntegrity.encrypt(data, password1, salt1)
    val decrypted1 = AesCbcWithIntegrity.decrypt(encrypted1, password1, salt1)
    val encrypted2 = AesCbcWithIntegrity.encrypt(data, password2, salt2)
    val decrypted2 = AesCbcWithIntegrity.decrypt(encrypted2, password2, salt2)
    assertEquals(decrypted1, decrypted2)
  }

  @Test
  fun givenAString_whenItIsEncryptedAndDecrypted_thenTheDecryptedStringEqualsTheOriginalString() {
    val password = "password"
    val salt = "salt"
    val data = "This is a test string"
    val encrypted = AesCbcWithIntegrity.encrypt(data, password, salt)
    val decrypted = AesCbcWithIntegrity.decrypt(encrypted, password, salt)
    assertEquals(data, decrypted)
  }

}