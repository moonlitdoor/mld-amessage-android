package com.moonlitdoor.amessage.domain.work

import com.moonlitdoor.amessage.database.entity.ConnectionEntity
import com.moonlitdoor.amessage.database.entity.ProfileEntity
import com.moonlitdoor.amessage.domain.model.Profile
import com.moonlitdoor.amessage.network.client.FirebaseClient
import org.koin.core.KoinComponent
import org.koin.core.inject

class ConnectionInviteWorker : KoinComponent {

  private val client: FirebaseClient by inject()

  fun doWork() {
//    inputData.getString(PAYLOAD_HANDLE)?.let { payloadHandle ->
//      inputData.getString(PAYLOAD_TOKEN)?.let { payloadToken ->
//        inputData.getString(PAYLOAD_CONNECTION_ID)?.let { payloadConnectionId ->
//          inputData.getString(PAYLOAD_PASSWORD)?.let { payloadPassword ->
//            inputData.getString(PAYLOAD_SALT)?.let { payloadSalt ->
//              inputData.getString(CONNECTION_ID)?.let { connectionId ->
//                inputData.getString(CONNECTION_TOKEN)?.let { connectionToken ->
//                  inputData.getString(CONNECTION_PASSWORD)?.let { connectionPassword ->
//                    inputData.getString(CONNECTION_SALT)?.let { connectionSalt ->
//                      //                      val response =
//                      client.send(
//                        FirebaseMessageJson(
//                          ConnectionInvitePayload(
//                            payloadHandle,
//                            payloadToken,
//                            UUID.fromString(payloadConnectionId),
//                            UUID.fromString(payloadPassword),
//                            UUID.fromString(payloadSalt)
//                          ),
//                          UUID.fromString(connectionId),
//                          connectionToken,
//                          UUID.fromString(connectionPassword),
//                          UUID.fromString(connectionSalt)
//                        )
//                      ).execute()
//                    }
//                  }
//                }
//              }
//            }
//          }
//        }
//      }
//    }

//    return Result.failure()
  }

  companion object {

    private const val PAYLOAD_HANDLE = "com.moonlitdoor.amessage.domain.work.ConnectionInviteWorker.PAYLOAD_HANDLE"
    private const val PAYLOAD_TOKEN = "com.moonlitdoor.amessage.domain.work.ConnectionInviteWorker.PAYLOAD_TOKEN"
    private const val PAYLOAD_CONNECTION_ID = "com.moonlitdoor.amessage.domain.work.ConnectionInviteWorker.PAYLOAD_CONNECTION_ID"
    private const val PAYLOAD_PASSWORD = "com.moonlitdoor.amessage.domain.work.ConnectionInviteWorker.PAYLOAD_PASSWORD"
    private const val PAYLOAD_SALT = "com.moonlitdoor.amessage.domain.work.ConnectionInviteWorker.PAYLOAD_PASSWORD"
    private const val CONNECTION_ID = "com.moonlitdoor.amessage.domain.work.ConnectionInviteWorker.CONNECTION_ID"
    private const val CONNECTION_TOKEN = "com.moonlitdoor.amessage.domain.work.ConnectionInviteWorker.CONNECTION_TOKEN"
    private const val CONNECTION_PASSWORD = "com.moonlitdoor.amessage.domain.work.ConnectionInviteWorker.CONNECTION_PASSWORD"
    private const val CONNECTION_SALT = "com.moonlitdoor.amessage.domain.work.ConnectionInviteWorker.CONNECTION_SALT"

    fun data(it: ProfileEntity, entity: ConnectionEntity, profile: Profile) = mapOf(
      PAYLOAD_HANDLE to it.handle,
      PAYLOAD_TOKEN to it.token,
      PAYLOAD_CONNECTION_ID to entity.connectionId.toString(),
      PAYLOAD_PASSWORD to entity.password.toString(),
      PAYLOAD_SALT to entity.salt.toString(),
      CONNECTION_ID to profile.id.toString(),
      CONNECTION_TOKEN to profile.token,
      CONNECTION_PASSWORD to profile.password.toString(),
      CONNECTION_SALT to profile.salt.toString()
    )

  }

}