package com.moonlitdoor.amessage.encryption

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import java.util.UUID

@RunWith(AndroidJUnit4::class)
class AuthenticatedEncryptionWithAssociatedDataTest {

  @Test
  fun givenAString_whenItIsEncrypted_thenItDoesNotEqualTheString() {
    val keys = AuthenticatedEncryptionWithAssociatedData.generateKeys()
    val associatedData = UUID.randomUUID()
    val data = "This is a test string"
    val encrypted = AuthenticatedEncryptionWithAssociatedData.encrypt(data, keys, associatedData)
    Assert.assertNotEquals(data, encrypted)
  }

  @Test
  fun givenAString_whenItIsEncryptedTwiceWithKeys_thenTheTwoEncryptedStringsAreNotEqual() {
    val keys = AuthenticatedEncryptionWithAssociatedData.generateKeys()
    val associatedData = UUID.randomUUID()
    val data = "This is a test string"
    val encrypted1 = AuthenticatedEncryptionWithAssociatedData.encrypt(data, keys, associatedData)
    val encrypted2 = AuthenticatedEncryptionWithAssociatedData.encrypt(data, keys, associatedData)
    Assert.assertNotEquals(encrypted1, encrypted2)
  }

  @Test
  fun givenAString_whenItIsEncryptedTwiceWithDifferentKeysAndTheSameAssociatedData_thenTheTwoEncryptedStringsAreNotEqual() {
    val keys1 = AuthenticatedEncryptionWithAssociatedData.generateKeys()
    val keys2 = AuthenticatedEncryptionWithAssociatedData.generateKeys()
    val associatedData = UUID.randomUUID()
    val data = "This is a test string"
    val encrypted1 = AuthenticatedEncryptionWithAssociatedData.encrypt(data, keys1, associatedData)
    val encrypted2 = AuthenticatedEncryptionWithAssociatedData.encrypt(data, keys2, associatedData)
    Assert.assertNotEquals(encrypted1, encrypted2)
  }

  @Test
  fun givenAString_whenItIsEncryptedTwiceWithTheSameKeysAndDifferentAssociatedData_thenTheTwoEncryptedStringsAreNotEqual() {
    val keys = AuthenticatedEncryptionWithAssociatedData.generateKeys()
    val associatedData1 = UUID.randomUUID()
    val associatedData2 = UUID.randomUUID()
    val data = "This is a test string"
    val encrypted1 = AuthenticatedEncryptionWithAssociatedData.encrypt(data, keys, associatedData1)
    val encrypted2 = AuthenticatedEncryptionWithAssociatedData.encrypt(data, keys, associatedData2)
    Assert.assertNotEquals(encrypted1, encrypted2)
  }

  @Test
  fun givenAString_whenItIsEncryptedTwiceWithDifferentKeysAndDifferentAssociatedData_thenTheTwoEncryptedStringsAreNotEqual() {
    val keys1 = AuthenticatedEncryptionWithAssociatedData.generateKeys()
    val keys2 = AuthenticatedEncryptionWithAssociatedData.generateKeys()
    val associatedData1 = UUID.randomUUID()
    val associatedData2 = UUID.randomUUID()
    val data = "This is a test string"
    val encrypted1 = AuthenticatedEncryptionWithAssociatedData.encrypt(data, keys1, associatedData1)
    val encrypted2 = AuthenticatedEncryptionWithAssociatedData.encrypt(data, keys2, associatedData2)
    Assert.assertNotEquals(encrypted1, encrypted2)
  }

  @Test
  fun givenAString_whenItIsEncryptedAndDecryptedWithDifferentKeysAndDifferentAssociatedData_thenTheTwoDecryptedStringsAreEqual() {
    val keys1 = AuthenticatedEncryptionWithAssociatedData.generateKeys()
    val keys2 = AuthenticatedEncryptionWithAssociatedData.generateKeys()
    val associatedData1 = UUID.randomUUID()
    val associatedData2 = UUID.randomUUID()
    val data = "This is a test string"
    val encrypted1 = AuthenticatedEncryptionWithAssociatedData.encrypt(data, keys1, associatedData1)
    val decrypted1 = AuthenticatedEncryptionWithAssociatedData.decrypt(encrypted1, keys1, associatedData1)
    val encrypted2 = AuthenticatedEncryptionWithAssociatedData.encrypt(data, keys2, associatedData2)
    val decrypted2 = AuthenticatedEncryptionWithAssociatedData.decrypt(encrypted2, keys2, associatedData2)
    Assert.assertEquals(decrypted1, decrypted2)
  }

  @Test
  fun givenAString_whenItIsEncryptedAndDecrypted_thenTheDecryptedStringEqualsTheOriginalString() {
    val keys = AuthenticatedEncryptionWithAssociatedData.generateKeys()
    val associatedData = UUID.randomUUID()
    val data = "This is a test string"
    val encrypted = AuthenticatedEncryptionWithAssociatedData.encrypt(data, keys, associatedData)
    val decrypted = AuthenticatedEncryptionWithAssociatedData.decrypt(encrypted, keys, associatedData)
    Assert.assertEquals(data, decrypted)
  }

  @Test
  fun givenAnEncryptedValueAssociatedDataAndSerializedKey_whenTheValueIsDecrypted_ThePlainTextIsCorrect() {
    val encryptedValue = "ARlSjRjHKigaeyLdg5UMcczFphhUvIF1VOqdcKx9hZGx33xO10eq97kkC71DFyTnjKFKr0vL"
    val associatedData = UUID.fromString("8fcb0a71-df67-44ac-87c3-31c705b1259b")
    val serializedKeys = """{
        "primaryKeyId": 424840472,
        "key": [
            {
                "keyData": {
                    "typeUrl": "type.googleapis.com\/google.crypto.tink.AesGcmKey",
                    "value": "GhCWJgUyyGiV4P8vKSmmMoX8",
                    "keyMaterialType": "SYMMETRIC"
                },
                "status": "ENABLED",
                "keyId": 424840472,
                "outputPrefixType": "TINK"
            }
        ]
    }
    """.trimIndent()
    val keys = AuthenticatedEncryptionWithAssociatedData.deserializeKeys(serializedKeys)
    val plainText = AuthenticatedEncryptionWithAssociatedData.decrypt(encryptedValue, keys, associatedData)
    Assert.assertEquals("This is a test string", plainText)
  }
}
