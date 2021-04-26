package com.moonlitdoor.amessage.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.moonlitdoor.amessage.database.entity.ConnectionEntity
import kotlinx.coroutines.flow.Flow
import java.util.UUID

@Dao
interface ConnectionDao {

  @Query("SELECT * FROM connection WHERE connection_id == :connectionId")
  suspend fun get(connectionId: UUID): ConnectionEntity

  @Query("SELECT * FROM connection WHERE connection_id == :connectionId")
  fun getFlow(connectionId: UUID): Flow<ConnectionEntity>

  @Query("SELECT * FROM connection WHERE state = 'connected'")
  fun getConnected(): Flow<List<ConnectionEntity>>

  @Query("SELECT * FROM connection WHERE state = 'invited'")
  fun getInvited(): Flow<List<ConnectionEntity>>

  @Query("SELECT * FROM connection WHERE state = 'pending'")
  fun getPending(): Flow<List<ConnectionEntity>>

  @Query("SELECT count(*) FROM connection")
  suspend fun connectionCount(): Long

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  suspend fun insert(connection: ConnectionEntity): Long

  @Update
  suspend fun update(connection: ConnectionEntity): Int

  @Query("UPDATE connection SET state = :state WHERE connection_id = :connectionId")
  suspend fun update(connectionId: UUID, state: ConnectionEntity.State)

  @Delete
  suspend fun delete(connection: ConnectionEntity)

  @Query("DELETE FROM connection WHERE connection_id == :connectionId")
  suspend fun delete(connectionId: UUID)

  @Query("SELECT count(*) FROM connection where connection_id == :connectionId")
  suspend fun isConnectionExisting(connectionId: UUID): Long
}
