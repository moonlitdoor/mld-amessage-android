package com.moonlitdoor.amessage.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.moonlitdoor.amessage.database.converter.AssociatedDateProjectionConverter
import com.moonlitdoor.amessage.database.converter.ConnectionStateConverter
import com.moonlitdoor.amessage.database.converter.DateConverter
import com.moonlitdoor.amessage.database.converter.HandleProjectionConverter
import com.moonlitdoor.amessage.database.converter.IdProjectionConverter
import com.moonlitdoor.amessage.database.converter.KeysProjectionConverter
import com.moonlitdoor.amessage.database.converter.TokenProjectionConverter
import com.moonlitdoor.amessage.database.converter.UuidConverter
import com.moonlitdoor.amessage.database.dao.ConnectionDao
import com.moonlitdoor.amessage.database.dao.ConversationDao
import com.moonlitdoor.amessage.database.dao.KeyValueDao
import com.moonlitdoor.amessage.database.dao.ProfileDao
import com.moonlitdoor.amessage.database.dao.SettingsDao
import com.moonlitdoor.amessage.database.entity.ConnectionEntity
import com.moonlitdoor.amessage.database.entity.ConversationConnectionEntity
import com.moonlitdoor.amessage.database.entity.ConversationEntity
import com.moonlitdoor.amessage.database.entity.KeyValueEntity
import com.moonlitdoor.amessage.database.view.ProfileView

@TypeConverters(
  value = [
    AssociatedDateProjectionConverter::class,
    ConnectionStateConverter::class,
    DateConverter::class,
    HandleProjectionConverter::class,
    IdProjectionConverter::class,
    KeysProjectionConverter::class,
    TokenProjectionConverter::class,
    UuidConverter::class,
  ]
)
@Database(
  version = Migrations.VERSION,
  entities = [
    KeyValueEntity::class,
    ConnectionEntity::class,
    ConversationEntity::class,
    ConversationConnectionEntity::class
  ],
  views = [
    ProfileView::class
  ]
)
abstract class AMessageDatabase : RoomDatabase() {

  abstract fun keyValueDao(): KeyValueDao

  abstract fun profileDao(): ProfileDao

  abstract fun settingDao(): SettingsDao

  abstract fun connectionDao(): ConnectionDao

  abstract fun conversationDao(): ConversationDao

  companion object {
    const val DATABASE_NAME = "amessage.db"
  }

}
