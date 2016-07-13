package com.moonlitdoor.amessage

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.view.ViewGroup

open class TestingActivity : androidx.fragment.app.FragmentActivity() {

  private val container = View(this).also {
    it.id = View.generateViewId()
    it.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
  }

  override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
    super.onCreate(savedInstanceState, persistentState)
    addContentView(container, ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))
  }

  protected fun setup() {
    supportFragmentManager.beginTransaction().add(ConnectFragment(), null).commitNow()
  }

}