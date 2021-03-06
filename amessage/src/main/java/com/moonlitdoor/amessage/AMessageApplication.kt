package com.moonlitdoor.amessage

import android.app.Application
import androidx.camera.camera2.Camera2Config
import androidx.camera.core.CameraXConfig
import androidx.hilt.work.HiltWorkerFactory
import androidx.lifecycle.LifecycleObserver
import androidx.work.Configuration
import com.google.firebase.FirebaseApp
import com.moonlitdoor.amessage.analytics.Analytics
import com.moonlitdoor.amessage.experiments.Experiments
import com.moonlitdoor.amessage.root.Root
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class AMessageApplication : Application(), LifecycleObserver, Configuration.Provider, CameraXConfig.Provider {

  @Inject
  lateinit var workerFactory: HiltWorkerFactory

  override fun onCreate() {
    Root.init(this)
    super.onCreate()
    UserId.init(this)
    FirebaseApp.initializeApp(this)
    TimberInit(BuildConfig.DEBUG)
    Analytics.init(this, UserId.value)
    Experiments.init()
  }

  override fun getCameraXConfig(): CameraXConfig = Camera2Config.defaultConfig()

  override fun getWorkManagerConfiguration(): Configuration =
    Configuration.Builder()
      .setMinimumLoggingLevel(android.util.Log.INFO)
      .setWorkerFactory(workerFactory)
      .build()
}
