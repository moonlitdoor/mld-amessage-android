package com.moonlitdoor.amessage.connect

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.ViewGroup
import com.google.android.gms.vision.CameraSource

class CameraPreview(context: Context, attrs: AttributeSet) : ViewGroup(context, attrs) {

  private val surfaceView: SurfaceView = SurfaceView(context)
  private var startRequested: Boolean = false
  private var surfaceAvailable: Boolean = false
  private var cameraSource: CameraSource? = null

  init {
    surfaceView.holder.addCallback(SurfaceCallback())
    addView(surfaceView)
  }

  fun start(cameraSource: CameraSource?) {
    cameraSource ?: stop()
    this.cameraSource = cameraSource
    this.cameraSource?.let {
      startRequested = true
      startIfReady()
    }
  }

  private fun stop() {
    cameraSource?.stop()
  }

  fun release() {
    cameraSource?.let {
      cameraSource = null
      try {
        it.release()
      } catch (ignored: NullPointerException) {

      }
    }
  }

  @SuppressLint("MissingPermission")
  private fun startIfReady() {
    if (startRequested && surfaceAvailable) {
      cameraSource?.let {
        it.start(surfaceView.holder)
//        GlobalScope.launch(Dispatchers.Main, CoroutineStart.DEFAULT, null, {
//          overlay.clear()
//        })
        startRequested = false
      }
    }
  }

  override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
    for (i in 0 until childCount) {
      getChildAt(i).layout(0, 0, width, height)
    }
    startIfReady()
  }

  private inner class SurfaceCallback : SurfaceHolder.Callback {
    override fun surfaceCreated(surface: SurfaceHolder) {
      surfaceAvailable = true
      startIfReady()
    }

    override fun surfaceDestroyed(surface: SurfaceHolder) {
      surfaceAvailable = false
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) = Unit
  }
}
