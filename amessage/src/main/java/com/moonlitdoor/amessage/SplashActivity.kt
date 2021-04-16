package com.moonlitdoor.amessage

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    Handler(Looper.getMainLooper()).postDelayed({
      AMessageActivity.start(this)
    }, 1000)
  }
}
