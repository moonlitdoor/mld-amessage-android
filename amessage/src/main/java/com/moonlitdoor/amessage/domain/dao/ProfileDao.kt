package com.moonlitdoor.amessage.domain.dao

import android.content.SharedPreferences
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.moonlitdoor.amessage.Constants.SharedPreferences.HANDLE
import com.moonlitdoor.amessage.Constants.SharedPreferences.ID
import com.moonlitdoor.amessage.Constants.SharedPreferences.PASSWORD
import com.moonlitdoor.amessage.Constants.SharedPreferences.SALT
import com.moonlitdoor.amessage.Constants.SharedPreferences.TOKEN
import com.moonlitdoor.amessage.domain.entity.ProfileEntity
import com.moonlitdoor.amessage.util.liveData
import java.util.*

open class ProfileDao constructor(val preferences: SharedPreferences) {

  open val handle: LiveData<String?> by lazy { preferences.liveData(HANDLE) }
  protected val token: LiveData<String?> by lazy { preferences.liveData(TOKEN) }
  protected val id: LiveData<String?> by lazy { preferences.liveData(ID).also { it.value ?: preferences.edit().putString(ID, UUID.randomUUID().toString()).apply() } }
  protected val password: LiveData<String?> by lazy { preferences.liveData(PASSWORD).also { it.value ?: preferences.edit().putString(PASSWORD, UUID.randomUUID().toString()).apply() } }
  protected val salt: LiveData<String?> by lazy { preferences.liveData(SALT).also { it.value ?: preferences.edit().putString(SALT, UUID.randomUUID().toString()).apply() } }

  val profile: LiveData<ProfileEntity?> by lazy {
    object : MediatorLiveData<ProfileEntity?>() {
      var h: String? = null
      var t: String? = null
      var i: UUID? = null
      var p: UUID? = null
      var s: UUID? = null
      fun build() =
        h?.let { _h ->
          t?.let { _t ->
            i?.let { _i ->
              p?.let { _p ->
                s?.let { _s ->
                  postValue(ProfileEntity(_h, _t, _i, _p, _s))
                }
              }
            }
          }
        }
    }.apply {
      addSource(handle) { h = it; build() }
      addSource(token) { t = it; build() }
      addSource(id) { i = UUID.fromString(it); build() }
      addSource(password) { p = UUID.fromString(it); build() }
      addSource(salt) { s = UUID.fromString(it); build() }
    }
  }

  fun getProfileSync(): ProfileEntity? =
    handle.value?.let { h ->
      token.value?.let { t ->
        id.value?.let { i ->
          password.value?.let { p ->
            salt.value?.let { s ->
              ProfileEntity(h, t, UUID.fromString(i), UUID.fromString(p), UUID.fromString(s))
            }
          }
        }
      }
    }


  @WorkerThread
  fun setHandle(handle: String) = preferences.edit().putString(HANDLE, handle).apply()

  @WorkerThread
  fun setToken(token: String) = preferences.edit().putString(TOKEN, token).apply()

}
