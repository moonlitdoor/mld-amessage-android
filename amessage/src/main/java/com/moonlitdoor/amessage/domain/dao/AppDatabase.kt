package com.moonlitdoor.amessage.domain.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.moonlitdoor.amessage.domain.dao.converter.ConnectionStateConverter
import com.moonlitdoor.amessage.domain.dao.converter.DateConverter
import com.moonlitdoor.amessage.domain.dao.converter.UuidConverter
import com.moonlitdoor.amessage.domain.entity.ConnectionEntity
import com.moonlitdoor.amessage.domain.entity.ConversationConnectionEntity
import com.moonlitdoor.amessage.domain.entity.ConversationEntity

@TypeConverters(value = [DateConverter::class, UuidConverter::class, ConnectionStateConverter::class])
@Database(
  version = Migrations.VERSION,
  entities = [ConnectionEntity::class,
    ConversationEntity::class,
    ConversationConnectionEntity::class
  ]
)
abstract class AppDatabase : RoomDatabase() {

  abstract fun connectionDao(): ConnectionDao

  abstract fun conversationDao(): ConversationDao

  companion object {
    const val DATABASE_NAME = "amessage.db"
  }

}
