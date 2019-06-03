@file:Suppress("RemoveExplicitTypeArguments")

package com.moonlitdoor.amessage.database

import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.room.Room
import com.moonlitdoor.amessage.constants.constantsDi
import com.moonlitdoor.amessage.database.dao.ProfileDao
import com.moonlitdoor.amessage.database.dao.ThemeDao
import com.moonlitdoor.amessage.database.dao.WindowsDao
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseDi = constantsDi + listOf(module {

  single { PreferenceManager.getDefaultSharedPreferences(androidContext()) }
  single { Room.databaseBuilder(androidContext(), AppDatabase::class.java, AppDatabase.DATABASE_NAME).addMigrations(*Migrations.ALL).build() }
  single { get<AppDatabase>().connectionDao() }
  single { get<AppDatabase>().conversationDao() }
  single { ProfileDao(get<SharedPreferences>()) }
  single { ThemeDao(get<SharedPreferences>()) }
  single { WindowsDao(androidContext(), get<SharedPreferences>()) }

})
