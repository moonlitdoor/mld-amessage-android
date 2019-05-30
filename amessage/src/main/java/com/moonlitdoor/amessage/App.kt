package com.moonlitdoor.amessage

//import com.crashlytics.android.Crashlytics
//import io.fabric.sdk.android.Fabric
import android.app.Application
import androidx.lifecycle.LifecycleObserver
import androidx.preference.PreferenceManager
import com.google.firebase.FirebaseApp
import com.moonlitdoor.amessage.domain.domainDi
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application(), LifecycleObserver {

  override fun onCreate() {
    super.onCreate()
    FirebaseApp.initializeApp(this)
    RemoteConfig.init()
    initTimber(BuildConfig.DEBUG)
    startKoin {
      androidContext(this@App)
      modules(listOf(di, domainDi))
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
    private const val CRASHLYTICS_USER_ID = "com.moonlitdoor.amessage.App.CRASHLYTICS_USER_ID"
  }
}
