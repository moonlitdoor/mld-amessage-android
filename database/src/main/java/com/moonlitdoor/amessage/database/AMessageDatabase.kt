package com.moonlitdoor.amessage.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.moonlitdoor.amessage.database.converter.ConnectionStateConverter
import com.moonlitdoor.amessage.database.converter.DateConverter
import com.moonlitdoor.amessage.database.converter.UuidConverter
import com.moonlitdoor.amessage.database.dao.ConnectionDao
import com.moonlitdoor.amessage.database.dao.ConversationDao
import com.moonlitdoor.amessage.database.entity.ConnectionEntity
import com.moonlitdoor.amessage.database.entity.ConversationConnectionEntity
import com.moonlitdoor.amessage.database.entity.ConversationEntity

@TypeConverters(value = [DateConverter::class, UuidConverter::class, ConnectionStateConverter::class])
@Database(
  version = Migrations.VERSION,
  entities = [ConnectionEntity::class,
    ConversationEntity::class,
    ConversationConnectionEntity::class
  ]
)
abstract class AMessageDatabase : RoomDatabase() {

  abstract fun connectionDao(): ConnectionDao

  abstract fun conversationDao(): ConversationDao

  companion object {
    const val DATABASE_NAME = "amessage.db"
  }

}
