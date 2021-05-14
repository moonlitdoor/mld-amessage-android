package com.moonlitdoor.amessage

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.work.WorkManager
import com.moonlitdoor.amessage.init.DatabasePopulationWorker
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

  @Inject
  lateinit var workManager: WorkManager

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    DatabasePopulationWorker.enqueue(workManager = workManager)

    Handler(Looper.getMainLooper()).postDelayed(
      {
        AMessageActivity.start(this)
      },
      1000
    )
  }
}
