package com.moonlitdoor.amessage.database

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.room.Room
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.moonlitdoor.amessage.constants.ConstantsDI
import com.moonlitdoor.amessage.database.dao.ConnectionDao
import com.moonlitdoor.amessage.database.dao.ConversationDao
import com.moonlitdoor.amessage.database.dao.FrequentlyAskedQuestionDao
import com.moonlitdoor.amessage.database.dao.ProfileDao
import com.moonlitdoor.amessage.database.dao.ThemeDao
import com.moonlitdoor.amessage.database.dao.WindowsDao
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Scope

@Component(
  modules = [DatabaseDI.DatabaseModule::class],
  dependencies = [ConstantsDI::class]
)
@DatabaseDI.DatabaseScope
interface DatabaseDI {

  fun connectionDao(): ConnectionDao
  fun conversationDao(): ConversationDao
  fun frequentlyAskedQuestionDao(): FrequentlyAskedQuestionDao
  fun profileDao(): ProfileDao
  fun themeDao(): ThemeDao
  fun windowsDao(): WindowsDao

  @Scope
  @kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
  annotation class DatabaseScope


  @Module
  class DatabaseModule(private var context: Context) {

    @Provides
    fun providesSharedPreferences(): SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    @Provides
    @DatabaseScope
    fun providesRealtimeDatabase(): FirebaseDatabase = Firebase.database.apply {
      setPersistenceEnabled(true)
    }

    @Provides
    fun providesDatabaseReference(database: FirebaseDatabase): DatabaseReference = database.getReference("faq").apply {
      keepSynced(true)
    }

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
