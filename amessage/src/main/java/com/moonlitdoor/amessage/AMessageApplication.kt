package com.moonlitdoor.amessage

import android.app.Application
import androidx.camera.camera2.Camera2Config
import androidx.camera.core.CameraXConfig
import androidx.lifecycle.LifecycleObserver
import androidx.preference.PreferenceManager
import com.google.firebase.FirebaseApp
import com.moonlitdoor.amessage.analytics.Analytics
import com.moonlitdoor.amessage.experiments.Experiments
import com.moonlitdoor.amessage.root.Root

class AMessageApplication : Application(), LifecycleObserver, CameraXConfig.Provider, Provider by AMessageDI {

  override fun onCreate() {
    Root.init(this)
    super.onCreate()
    UserId.init(this)
    FirebaseApp.initializeApp(this)
    TimberInit(BuildConfig.DEBUG)
    Analytics.init(this, UserId.value)
    AMessageDI.init(this)
    Experiments.init()
    PreferenceManager.setDefaultValues(this, R.xml.preferences, false)
  }

  override fun getCameraXConfig(): CameraXConfig = Camera2Config.defaultConfig()

}
