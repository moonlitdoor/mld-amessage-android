package com.moonlitdoor.amessage.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.moonlitdoor.amessage.database.entity.ConnectionEntity
import kotlinx.coroutines.flow.Flow
import java.util.*

@Dao
interface ConnectionDao {

  @Query("SELECT * FROM connection WHERE state = 'connected'")
  fun getConnected(): Flow<List<ConnectionEntity>>

  @Query("SELECT * FROM connection WHERE state = 'invited'")
  fun getInvited(): Flow<List<ConnectionEntity>>

  @Query("SELECT * FROM connection WHERE state = 'pending'")
  fun getPending(): Flow<List<ConnectionEntity>>

  @Query("SELECT count(*) FROM connection")
  suspend fun connectionCount(): Long

  @Query("SELECT * FROM connection WHERE state = 'invited'")
  fun getInvitedConnections(): Flow<List<ConnectionEntity>>

  @Query("SELECT * FROM connection WHERE state IN ('scanned','pending', 'invited')")
  fun getScannedInvitedAndPendingConnections(): LiveData<List<ConnectionEntity>>

  @Query("SELECT * FROM connection WHERE connection_id == :connectionId")
  fun get(connectionId: UUID): ConnectionEntity

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  suspend fun insert(connection: ConnectionEntity): Long

  @Update
  fun update(connection: ConnectionEntity)

  @Query("UPDATE connection SET state = :state WHERE connection_id = :connectionId")
  fun update(connectionId: UUID, state: ConnectionEntity.State)

  @Delete
  fun delete(connection: ConnectionEntity)

  @Query("DELETE FROM connection WHERE connection_id == :connectionId")
  fun delete(connectionId: UUID)

  @Query("SELECT * FROM connection WHERE id == :connectionId")
  fun getConnection(connectionId: Long): Flow<ConnectionEntity>

  @Query("SELECT count(*) FROM connection where connection_id == :connectionId")
  suspend fun isConnectionExisting(connectionId: UUID): Long

}
