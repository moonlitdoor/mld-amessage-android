package com.moonlitdoor.amessage.encryption

import com.google.crypto.tink.Aead
import com.google.crypto.tink.CleartextKeysetHandle
import com.google.crypto.tink.JsonKeysetReader
import com.google.crypto.tink.JsonKeysetWriter
import com.google.crypto.tink.KeysetHandle
import com.google.crypto.tink.aead.AeadConfig
import com.google.crypto.tink.aead.AesGcmKeyManager
import java.io.ByteArrayOutputStream
import java.util.*


object AuthenticatedEncryptionWithAssociatedData {

  init {
    AeadConfig.register();
  }

  fun generateKeys(): KeysetHandle = KeysetHandle.generateNew(AesGcmKeyManager.aes128GcmTemplate())

  fun serializeKeys(keys: KeysetHandle): String {
    val stream = ByteArrayOutputStream()
    CleartextKeysetHandle.write(keys, JsonKeysetWriter.withOutputStream(stream))
    return String(stream.toByteArray())
  }

  fun generateSerializedKeys(): String = serializeKeys(generateKeys())

  fun deserializeKeys(keys: String): KeysetHandle = CleartextKeysetHandle.read(JsonKeysetReader.withBytes(keys.toByteArray()))

  fun encrypt(data: String, keys: KeysetHandle, associatedData: UUID): String = String(Base64.getEncoder().encode(keys.getPrimitive(Aead::class.java).encrypt(data.toByteArray(), associatedData.toString().toByteArray())))

  fun encrypt(data: String, keys: String, associatedData: UUID) = encrypt(data, deserializeKeys(keys), associatedData)

  fun decrypt(data: String, keys: KeysetHandle, associatedData: UUID): String = String(keys.getPrimitive(Aead::class.java).decrypt(Base64.getDecoder().decode(data), associatedData.toString().toByteArray()))

  fun decrypt(data: String, keys: String, associatedData: UUID) = decrypt(data, deserializeKeys(keys), associatedData)
}
