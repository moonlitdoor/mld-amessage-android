package com.moonlitdoor.amessage.di

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import com.moonlitdoor.amessage.domain.dao.ProfileDao
import com.moonlitdoor.amessage.domain.dao.ProfileDaoFake

class DependencyModuleFake(context: Context, private val handle: LiveData<String?>) {

  fun providesProfileDao(preferences: SharedPreferences): ProfileDao = ProfileDaoFake(preferences, handle)

}