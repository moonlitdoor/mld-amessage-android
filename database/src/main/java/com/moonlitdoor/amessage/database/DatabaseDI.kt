package com.moonlitdoor.amessage.database

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.moonlitdoor.amessage.database.dao.ConnectionDao
import com.moonlitdoor.amessage.database.dao.ConversationDao
import com.moonlitdoor.amessage.database.dao.KeyValueDao
import com.moonlitdoor.amessage.database.dao.ProfileDao
import com.moonlitdoor.amessage.database.dao.ThemeDao
import com.moonlitdoor.amessage.database.dao.WindowsDao
import dagger.Module
import dagger.Provides

interface DatabaseDI {

  @Module
  class DatabaseModule {

    @Provides
    fun providesRealtimeDatabase(): FirebaseDatabase = Firebase.database.apply {
      setPersistenceEnabled(true)
    }

    @Provides
    fun providesDatabaseReference(database: FirebaseDatabase): DatabaseReference = database.getReference("faq").apply {
      keepSynced(true)
    }

    @Provides
    fun providesAppDatabase(context: Context): AMessageDatabase = Room.databaseBuilder(context, AMessageDatabase::class.java, AMessageDatabase.DATABASE_NAME).addMigrations(*Migrations.ALL).build()

    @Provides
    fun providesConnectionDao(aMessageDatabase: AMessageDatabase): ConnectionDao = aMessageDatabase.connectionDao()

    @Provides
    fun providesConversationDao(aMessageDatabase: AMessageDatabase): ConversationDao = aMessageDatabase.conversationDao()

    @Provides
    fun providesKeyValueDao(aMessageDatabase: AMessageDatabase): KeyValueDao = aMessageDatabase.keyValueDao()

    @Provides
    fun providesProfileDao(aMessageDatabase: AMessageDatabase): ProfileDao = aMessageDatabase.profileDao()

    @Provides
    fun providesThemeDao(sharedPreferences: SharedPreferences): ThemeDao = ThemeDao(sharedPreferences)

    @Provides
    fun providesWindowsDao(sharedPreferences: SharedPreferences): WindowsDao = WindowsDao(sharedPreferences)

  }
}
