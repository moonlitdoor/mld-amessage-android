package com.moonlitdoor.amessage.util

import android.util.Base64
import java.nio.charset.Charset
import java.security.GeneralSecurityException
import java.security.SecureRandom
import javax.crypto.Cipher
import javax.crypto.Mac
import javax.crypto.SecretKey
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.PBEKeySpec
import javax.crypto.spec.SecretKeySpec

object AesCbcWithIntegrity {

  private const val CIPHER_TRANSFORMATION = "AES/CBC/PKCS5Padding"
  private const val CIPHER = "AES"
  private const val AES_KEY_LENGTH_BITS = 128
  private const val IV_LENGTH_BYTES = 16
  private const val PBE_ITERATION_COUNT = 10000
  private const val PBE_ALGORITHM = "PBKDF2WithHmacSHA1"
  private const val HMAC_ALGORITHM = "HmacSHA256"
  private const val HMAC_KEY_LENGTH_BITS = 256

  fun encrypt(plaintext: String, password: String, salt: String): String {
    val random = SecureRandom()
    val b = ByteArray(IV_LENGTH_BYTES)
    random.nextBytes(b)
    var iv = b
    val secretKeys = generateKeyFromPassword(password, salt)
    val aesCipherForEncryption = Cipher.getInstance(CIPHER_TRANSFORMATION)
    aesCipherForEncryption.init(Cipher.ENCRYPT_MODE, secretKeys.confidentialityKey, IvParameterSpec(iv))
    iv = aesCipherForEncryption.iv
    val byteCipherText = aesCipherForEncryption.doFinal(plaintext.toByteArray(charset("UTF-8")))
    val ivCipherConcat = ivCipherConcat(iv, byteCipherText)
    val integrityMac = generateMac(ivCipherConcat, secretKeys.integrityKey)
    return CipherTextIvMac(byteCipherText, iv, integrityMac).toString()
  }

  fun decrypt(encryptedPayload: String, password: String, salt: String): String {
    val civ = CipherTextIvMac(encryptedPayload)
    val secretKeys = generateKeyFromPassword(password, salt)
    val ivCipherConcat = ivCipherConcat(civ.iv, civ.cipherText)
    val computedMac = generateMac(ivCipherConcat, secretKeys.integrityKey)
    var result = 0
    for (i in computedMac.indices) {
      result = result or (computedMac[i].toInt() xor civ.mac[i].toInt())
    }
    if (computedMac.size == civ.mac.size && result == 0) {
      val aesCipherForDecryption = Cipher.getInstance(CIPHER_TRANSFORMATION)
      aesCipherForDecryption.init(Cipher.DECRYPT_MODE, secretKeys.confidentialityKey, IvParameterSpec(civ.iv))
      return String(aesCipherForDecryption.doFinal(civ.cipherText), Charset.forName("UTF-8"))
    } else {
      throw GeneralSecurityException("MAC stored in civ does not match computed MAC.")
    }
  }

  private fun generateKeyFromPassword(password: String, salt: String): SecretKeys {
    val keySpec = PBEKeySpec(
      password.toCharArray(), Base64.decode(salt, Base64.NO_WRAP),
      PBE_ITERATION_COUNT, AES_KEY_LENGTH_BITS + HMAC_KEY_LENGTH_BITS
    )
    val keyFactory = SecretKeyFactory.getInstance(PBE_ALGORITHM)
    val keyBytes = keyFactory.generateSecret(keySpec).encoded
    val confidentialityKeyBytes = copyOfRange(keyBytes, 0, AES_KEY_LENGTH_BITS / 8)
    val integrityKeyBytes = copyOfRange(
      keyBytes,
      AES_KEY_LENGTH_BITS / 8,
      AES_KEY_LENGTH_BITS / 8 + HMAC_KEY_LENGTH_BITS / 8
    )
    val confidentialityKey = SecretKeySpec(confidentialityKeyBytes, CIPHER)
    val integrityKey = SecretKeySpec(integrityKeyBytes, HMAC_ALGORITHM)
    return SecretKeys(confidentialityKey, integrityKey)
  }

  private fun generateMac(byteCipherText: ByteArray, integrityKey: SecretKey?): ByteArray {
    val sha256HMAC = Mac.getInstance(HMAC_ALGORITHM)
    sha256HMAC.init(integrityKey)
    return sha256HMAC.doFinal(byteCipherText)
  }

  private fun copyOfRange(from: ByteArray, start: Int, end: Int): ByteArray {
    val length = end - start
    val result = ByteArray(length)
    System.arraycopy(from, start, result, 0, length)
    return result
  }

  private fun ivCipherConcat(iv: ByteArray, cipherText: ByteArray): ByteArray {
    val combined = ByteArray(iv.size + cipherText.size)
    System.arraycopy(iv, 0, combined, 0, iv.size)
    System.arraycopy(cipherText, 0, combined, iv.size, cipherText.size)
    return combined
  }

  private data class SecretKeys(var confidentialityKey: SecretKey, var integrityKey: SecretKey) {
    override fun toString() = "${Base64.encodeToString(confidentialityKey.encoded, Base64.NO_WRAP)}:${Base64.encodeToString(integrityKey.encoded, Base64.NO_WRAP)}"
  }

  private class CipherTextIvMac(val cipherText: ByteArray, val iv: ByteArray, val mac: ByteArray) {

    constructor(base64IvAndCipherText: String) : this(
      Base64.decode(base64IvAndCipherText.split(":")[2], Base64.NO_WRAP),
      Base64.decode(base64IvAndCipherText.split(":")[0], Base64.NO_WRAP),
      Base64.decode(base64IvAndCipherText.split(":")[1], Base64.NO_WRAP)
    )

    override fun toString() = "${Base64.encodeToString(iv, Base64.NO_WRAP)}:${Base64.encodeToString(mac, Base64.NO_WRAP)}:${Base64.encodeToString(cipherText, Base64.NO_WRAP)}"
  }

}
