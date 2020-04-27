package com.moonlitdoor.amessage

import android.app.Application
import androidx.camera.camera2.Camera2Config
import androidx.camera.core.CameraXConfig
import androidx.lifecycle.LifecycleObserver
import androidx.preference.PreferenceManager
import androidx.work.Configuration
import androidx.work.WorkManager
import com.google.firebase.FirebaseApp
import com.moonlitdoor.amessage.analytics.Analytics
import com.moonlitdoor.amessage.domain.factory.AMessageWorkerFactory
import com.moonlitdoor.amessage.experiments.Experiments
import com.moonlitdoor.amessage.root.Root
import javax.inject.Inject

class AMessageApplication : Application(), LifecycleObserver, Configuration.Provider, CameraXConfig.Provider, Provider by AMessageDI {

  @Inject
  lateinit var workerFactory: AMessageWorkerFactory

  override fun onCreate() {
    Root.init(this)
    super.onCreate()
    UserId.init(this)
    FirebaseApp.initializeApp(this)
    TimberInit(BuildConfig.DEBUG)
    Analytics.init(this, UserId.value)
    AMessageDI.init(this).inject(this)
    Experiments.init()
    WorkManager.initialize(this, this.workManagerConfiguration)
    PreferenceManager.setDefaultValues(this, R.xml.preferences, false)
  }

  override fun getCameraXConfig(): CameraXConfig = Camera2Config.defaultConfig()

  override fun getWorkManagerConfiguration(): Configuration =
    Configuration.Builder()
      .setMinimumLoggingLevel(android.util.Log.INFO)
      .setWorkerFactory(workerFactory)
      .build()
}
