package com.moonlitdoor.amessage.database

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.room.Room
import com.moonlitdoor.amessage.constants.ConstantsDI
import com.moonlitdoor.amessage.database.dao.*
import dagger.Component
import dagger.Module
import dagger.Provides

@Component(
  modules = [DatabaseDI.DatabaseModule::class],
  dependencies = [ConstantsDI::class]
)
interface DatabaseDI {

  fun connectionDao(): ConnectionDao
  fun conversationDao(): ConversationDao
  fun profileDao(): ProfileDao
  fun themeDao(): ThemeDao
  fun windowsDao(): WindowsDao

  @Module
  class DatabaseModule(private var context: Context) {

    @Provides
    fun providesSharedPreferences(): SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    @Provides
    fun providesAppDatabase(): AMessageDatabase = Room.databaseBuilder(context, AMessageDatabase::class.java, AMessageDatabase.DATABASE_NAME).addMigrations(*Migrations.ALL).build()

    @Provides
    fun providesConnectionDao(AMessageDatabase: AMessageDatabase): ConnectionDao = AMessageDatabase.connectionDao()

    @Provides
    fun providesConversationDao(AMessageDatabase: AMessageDatabase): ConversationDao = AMessageDatabase.conversationDao()

    @Provides
    fun providesProfileDao(sharedPreferences: SharedPreferences): ProfileDao = ProfileDao(sharedPreferences)

    @Provides
    fun providesThemeDao(sharedPreferences: SharedPreferences): ThemeDao = ThemeDao(sharedPreferences)

    @Provides
    fun providesWindowsDao(sharedPreferences: SharedPreferences): WindowsDao = WindowsDao(sharedPreferences)

  }

  companion object {

    private var component: DatabaseDI? = null

    @Synchronized
    fun init(context: Context): DatabaseDI = component ?: DaggerDatabaseDI.builder()
      .constantsDI(ConstantsDI.init(context))
      .databaseModule(DatabaseModule(context))
      .build().also {
        component = it
      }


    @Synchronized
    fun get(): DatabaseDI = component ?: run { throw Exception("Not Initialized") }

  }

}
