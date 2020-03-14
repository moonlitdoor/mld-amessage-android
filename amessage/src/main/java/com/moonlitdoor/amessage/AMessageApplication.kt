package com.moonlitdoor.amessage

//import com.crashlytics.android.Crashlytics
//import io.fabric.sdk.android.Fabric
import android.app.Application
import androidx.camera.camera2.Camera2Config
import androidx.camera.core.CameraXConfig
import androidx.lifecycle.LifecycleObserver
import androidx.preference.PreferenceManager
import com.google.firebase.FirebaseApp
import com.moonlitdoor.amessage.analytics.Analytics
import com.moonlitdoor.amessage.experiments.Experiments

class AMessageApplication : Application(), LifecycleObserver, CameraXConfig.Provider {

  override fun onCreate() {
    super.onCreate()
    FirebaseApp.initializeApp(this)
    initTimber(BuildConfig.DEBUG)
    AMessageDI.init(this)
    Experiments.init()
//    RemoteConfig.init()
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

  override fun getCameraXConfig(): CameraXConfig = Camera2Config.defaultConfig()

  companion object {
    private const val CRASHLYTICS_USER_ID = "com.moonlitdoor.amessage.AMessageApplication.CRASHLYTICS_USER_ID"
  }
}
