package com.moonlitdoor.amessage.domain.dao

import android.content.SharedPreferences
import androidx.lifecycle.LiveData

class ProfileDaoFake(preferences: SharedPreferences, override val handle: LiveData<String?>) : ProfileDao(preferences)