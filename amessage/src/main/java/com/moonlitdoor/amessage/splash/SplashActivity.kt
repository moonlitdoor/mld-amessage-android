package com.moonlitdoor.amessage.splash

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.moonlitdoor.amessage.navigation.NavigationActivity

class SplashActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    Handler().postDelayed({
      NavigationActivity.start(this)
    }, 1000)
  }

  override fun onDestroy() {
    super.onDestroy()

  }
}
