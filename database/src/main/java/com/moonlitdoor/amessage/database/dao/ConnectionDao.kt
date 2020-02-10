package com.moonlitdoor.amessage.database.dao

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.room.*
import com.moonlitdoor.amessage.database.entity.ConnectionEntity
import java.util.*

@Dao
interface ConnectionDao {

  @MainThread
  @Query("SELECT * FROM connection WHERE state = 'connected'")
  fun getConnected(): LiveData<List<ConnectionEntity>>

  @Query("SELECT * FROM connection WHERE state = 'connected'")
  suspend fun getConnected2(): List<ConnectionEntity>

  @MainThread
  @Query("SELECT * FROM connection WHERE state IN ('scanned','pending', 'invited')")
  fun getScannedInvitedAndPendingConnections(): LiveData<List<ConnectionEntity>>

  @WorkerThread
  @Query("SELECT * FROM connection WHERE connection_id == :connectionId")
  fun get(connectionId: UUID): ConnectionEntity

  @WorkerThread
  @Insert(onConflict = OnConflictStrategy.IGNORE)
  suspend fun insert(connection: ConnectionEntity): Long

  @WorkerThread
  @Update
  fun update(connection: ConnectionEntity)

  @WorkerThread
  @Query("UPDATE connection SET state = :state WHERE connection_id = :connectionId")
  fun update(connectionId: UUID, state: ConnectionEntity.State)

  @WorkerThread
  @Delete
  fun delete(connection: ConnectionEntity)

  @WorkerThread
  @Query("DELETE FROM connection WHERE connection_id == :connectionId")
  fun delete(connectionId: UUID)

}
