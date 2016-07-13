package com.moonlitdoor.amessage.connect

import com.google.android.gms.vision.Detector
import com.google.android.gms.vision.barcode.Barcode

class BarcodeProcessor(private val listener: BarcodeDetectionListener) : Detector.Processor<Barcode> {

  private var mostRecentValue: String? = null

  override fun receiveDetections(detections: Detector.Detections<Barcode>): Unit {//= detections.detectedItems.forEach { _, barcode ->
//TODO fix this
//      if (barcode.rawValue != mostRecentValue) {
//        mostRecentValue = barcode.rawValue
//        listener.onNewBarcodeDetected(barcode.rawValue)
//      }
  }


  override fun release() {
    mostRecentValue = null
  }

  interface BarcodeDetectionListener {
    fun onNewBarcodeDetected(barcodeValue: String)
  }
}