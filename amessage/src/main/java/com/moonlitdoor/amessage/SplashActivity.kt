package com.moonlitdoor.amessage

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    Handler().postDelayed({
      AMessageActivity.start(this)
    }, 1000)
  }

  override fun onDestroy() {
    super.onDestroy()

  }
}
