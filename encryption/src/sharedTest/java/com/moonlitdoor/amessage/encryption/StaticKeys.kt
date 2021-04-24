package com.moonlitdoor.amessage.encryption

object StaticKeys {

  val value = """{
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
}
