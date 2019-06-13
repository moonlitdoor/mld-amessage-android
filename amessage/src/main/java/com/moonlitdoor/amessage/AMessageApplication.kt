package com.moonlitdoor.amessage

//import com.crashlytics.android.Crashlytics
//import io.fabric.sdk.android.Fabric
import android.app.Application
import androidx.lifecycle.LifecycleObserver
import androidx.preference.PreferenceManager
import com.google.firebase.FirebaseApp
import com.moonlitdoor.amessage.analytics.Analytics
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AMessageApplication : Application(), LifecycleObserver {

  override fun onCreate() {
    super.onCreate()
    FirebaseApp.initializeApp(this)
    RemoteConfig.init()
    initTimber(BuildConfig.DEBUG)
    startKoin {
      androidContext(this@AMessageApplication)
      modules(di)
    }
//    if (!BuildConfig.DEBUG) {
//      Fabric.with(this, Crashlytics())
//      PreferenceManager.getDefaultSharedPreferences(this).let {
//        Crashlytics.setUserIdentifier(it.getString(CRASHLYTICS_USER_ID, null) ?: UUID.randomUUID().toString().also { id ->
//          it.edit().putString(CRASHLYTICS_USER_ID, id).apply()
//        })
//      }
//    }
    PreferenceManager.setDefaultValues(this, R.xml.preferences, false)
    Analytics.init(this)
  }

  companion object {
    private const val CRASHLYTICS_USER_ID = "com.moonlitdoor.amessage.AMessageApplication.CRASHLYTICS_USER_ID"
  }
}