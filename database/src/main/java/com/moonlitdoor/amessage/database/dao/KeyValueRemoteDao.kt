package com.moonlitdoor.amessage.database.dao

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.moonlitdoor.amessage.database.entity.KeyValueRemoteEntity
import com.moonlitdoor.amessage.database.qualifiers.DatabaseReferenceKeyValueRemote
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.sendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import timber.log.Timber
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
class KeyValueRemoteDao @Inject constructor(@DatabaseReferenceKeyValueRemote private val reference: DatabaseReference) {

  fun getAboutKeyValues(): Flow<List<KeyValueRemoteEntity>> = callbackFlow {
    val listener = reference.child(ABOUT).addValueEventListener(object : ValueEventListener {
      override fun onDataChange(snapshot: DataSnapshot) {
        this@callbackFlow.sendBlocking(
          snapshot.children.mapNotNull {
            it.getValue(KeyValueRemoteEntity::class.java)
          }
        )
      }

      override fun onCancelled(error: DatabaseError) = Timber.e(error.toException())
    })

    awaitClose { reference.removeEventListener(listener) }
  }

  companion object {
    private const val ABOUT = "about"
  }
}
