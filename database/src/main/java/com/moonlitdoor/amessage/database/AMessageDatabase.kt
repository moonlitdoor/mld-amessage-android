package com.moonlitdoor.amessage.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.moonlitdoor.amessage.database.converter.AssociatedDataProjectionConverter
import com.moonlitdoor.amessage.database.converter.ConnectionStateConverter
import com.moonlitdoor.amessage.database.converter.DateConverter
import com.moonlitdoor.amessage.database.converter.HandleProjectionConverter
import com.moonlitdoor.amessage.database.converter.IdProjectionConverter
import com.moonlitdoor.amessage.database.converter.InstantConverter
import com.moonlitdoor.amessage.database.converter.KeysProjectionConverter
import com.moonlitdoor.amessage.database.converter.TitleProjectionConverter
import com.moonlitdoor.amessage.database.converter.TokenProjectionConverter
import com.moonlitdoor.amessage.database.converter.TopicProjectionConverter
import com.moonlitdoor.amessage.database.converter.UuidConverter
import com.moonlitdoor.amessage.database.dao.ConnectionDao
import com.moonlitdoor.amessage.database.dao.ConversationDao
import com.moonlitdoor.amessage.database.dao.KeyValueDao
import com.moonlitdoor.amessage.database.dao.PayloadDao
import com.moonlitdoor.amessage.database.dao.ProfileDao
import com.moonlitdoor.amessage.database.dao.SettingsDao
import com.moonlitdoor.amessage.database.entity.ConnectionConversationEntity
import com.moonlitdoor.amessage.database.entity.ConnectionEntity
import com.moonlitdoor.amessage.database.entity.ConversationEntity
import com.moonlitdoor.amessage.database.entity.KeyValueEntity
import com.moonlitdoor.amessage.database.entity.PayloadEntity
import com.moonlitdoor.amessage.database.view.ProfileView

@TypeConverters(
  value = [
    AssociatedDataProjectionConverter::class,
    ConnectionStateConverter::class,
    DateConverter::class,
    HandleProjectionConverter::class,
    InstantConverter::class,
    IdProjectionConverter::class,
    KeysProjectionConverter::class,
    TitleProjectionConverter::class,
    TokenProjectionConverter::class,
    TopicProjectionConverter::class,
    UuidConverter::class,
  ]
)
@Database(
  version = Migrations.VERSION,
  entities = [
    KeyValueEntity::class,
    ConnectionEntity::class,
    ConversationEntity::class,
    ConnectionConversationEntity::class,
    PayloadEntity::class
  ],
  views = [
    ProfileView::class
  ]
)
abstract class AMessageDatabase : RoomDatabase() {

  abstract fun connectionDao(): ConnectionDao

  abstract fun conversationDao(): ConversationDao

  abstract fun keyValueDao(): KeyValueDao

  abstract fun payloadDao(): PayloadDao

  abstract fun profileDao(): ProfileDao

  abstract fun settingDao(): SettingsDao

  companion object {
    const val DATABASE_NAME = "amessage.db"
  }
}
