package com.moonlitdoor.amessage.dto

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.moonlitdoor.amessage.dto.adapters.InstantAdapter
import com.moonlitdoor.amessage.encryption.AuthenticatedEncryptionWithAssociatedData
import java.time.Instant

abstract class Payload {

  abstract val type: Type

  sealed class Type(val value: String) {
    object ConnectionInvite : Type("ASFVASDFSV")
    object ConnectionConfirmation : Type("BERTYHSAFDV")
    object ConnectionRejection : Type("ETGASDFVB")
  }

  final override fun toString(): String = GSON.toJson(this)

  companion object {

    internal val GSON: Gson = GsonBuilder().registerTypeAdapter(Instant::class.java, InstantAdapter).create()

    fun encrypt(payload: String, keys: KeysDto, associatedData: AssociatedDataDto): String =
      AuthenticatedEncryptionWithAssociatedData.encrypt(payload, AuthenticatedEncryptionWithAssociatedData.deserializeKeys(keys.value), associatedData.value)


    fun decrypt(encryptedPayload: String, keys: KeysDto, associatedData: AssociatedDataDto): String =
      AuthenticatedEncryptionWithAssociatedData.decrypt(encryptedPayload, keys.value, associatedData.value)

  }

}
