package com.moonlitdoor.amessage.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.moonlitdoor.amessage.database.entity.KeyValueEntity
import com.moonlitdoor.amessage.database.projection.AssociatedDataProjection
import com.moonlitdoor.amessage.database.projection.HandleProjection
import com.moonlitdoor.amessage.database.projection.IdProjection
import com.moonlitdoor.amessage.database.projection.KeysProjection
import com.moonlitdoor.amessage.database.projection.TokenProjection
import com.moonlitdoor.amessage.database.view.ProfileView
import com.moonlitdoor.amessage.encryption.AuthenticatedEncryptionWithAssociatedData
import kotlinx.coroutines.flow.Flow
import java.util.UUID

@Dao
interface ProfileDao : KeyValueDao {

  @Query("SELECT * FROM profile")
  fun getProfileFlow(): Flow<ProfileView>

  @Query("SELECT * FROM profile")
  suspend fun getProfile(): ProfileView

  suspend fun setHandle(value: HandleProjection): Unit = setKeyValue(KeyValueEntity.from(value))

  suspend fun setToken(value: TokenProjection): Unit = setKeyValue(KeyValueEntity.from(value))

  suspend fun getId(): IdProjection = IdProjection(getValue(IdProjection.KEY)?.let { it.toUUID() } ?: UUID.randomUUID().also { insertId(IdProjection(it)) })

  suspend fun getKeys(): KeysProjection = KeysProjection(getValue(KeysProjection.KEY) ?: AuthenticatedEncryptionWithAssociatedData.generateSerializedKeys().also { insertKeys(KeysProjection(it)) })

  suspend fun getAssociatedData(): AssociatedDataProjection = AssociatedDataProjection(getValue(AssociatedDataProjection.KEY)?.let { it.toUUID() } ?: UUID.randomUUID().also { insertAssociatedData(AssociatedDataProjection(it)) })

  private suspend fun insertId(value: IdProjection): Unit = setKeyValue(KeyValueEntity.from(value))

  private suspend fun insertKeys(value: KeysProjection): Unit = setKeyValue(KeyValueEntity.from(value))

  private suspend fun insertAssociatedData(value: AssociatedDataProjection): Unit = setKeyValue(KeyValueEntity.from(value))

  private fun String?.toUUID() = this?.let { UUID.fromString(it) }
}
