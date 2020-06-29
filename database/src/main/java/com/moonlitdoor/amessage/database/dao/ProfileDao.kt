package com.moonlitdoor.amessage.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.moonlitdoor.amessage.database.projection.HandleProjection
import com.moonlitdoor.amessage.database.view.ProfileView
import kotlinx.coroutines.flow.Flow

@Dao
interface ProfileDao {

  @Query("SELECT * FROM profile")
  fun getProfile(): Flow<ProfileView>

  @Query("SELECT value FROM key_value WHERE `key` = 'handle'")
  fun getHandle(): Flow<HandleProjection>

  @Query("INSERT INTO key_value (`key`, value) VALUES ('handle', :value)")
  suspend fun insertHandle(value: String)

  @Query("INSERT INTO key_value (`key`, value) VALUES ('token', :value)")
  suspend fun insertToken(value: String)

}
