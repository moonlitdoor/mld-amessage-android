package com.moonlitdoor.amessage.database.dao

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.moonlitdoor.amessage.database.entity.FrequentlyAskedQuestionEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.sendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import timber.log.Timber
import javax.inject.Inject

class FrequentlyAskedQuestionDao @Inject constructor(private val reference: DatabaseReference) {

  @ExperimentalCoroutinesApi
  fun getFrequentlyAskedQuestions(): Flow<List<FrequentlyAskedQuestionEntity>> = callbackFlow {
    val listener = reference.child("questions").addValueEventListener(object : ValueEventListener {
      override fun onDataChange(snapshot: DataSnapshot) {
        this@callbackFlow.sendBlocking(snapshot.children.mapNotNull {
          it.getValue(FrequentlyAskedQuestionEntity::class.java)
        })
      }

      override fun onCancelled(error: DatabaseError) = Timber.e(error.toException())
    })

    awaitClose { reference.removeEventListener(listener) }
  }
}
