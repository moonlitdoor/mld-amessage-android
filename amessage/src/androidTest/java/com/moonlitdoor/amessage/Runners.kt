@file:Suppress("UNUSED")

package com.moonlitdoor.amessage

import android.os.Bundle
import androidx.test.filters.FlakyTest
import androidx.test.filters.LargeTest
import androidx.test.filters.MediumTest
import androidx.test.filters.SmallTest
import androidx.test.runner.AndroidJUnitRunner

/**
 *
 *  See the documentation for {@link android.support.test.runner.AndroidJUnitRunner} for options that can be put into the arguments
 *
 **/

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.TYPE)
annotation class SmokeTest

class AndroidJUnitRunnerSmoke : AndroidJUnitRunner() {
  override fun onCreate(arguments: Bundle?) {
    arguments?.putString("annotation", SmokeTest::class.java.canonicalName)
    super.onCreate(arguments)
  }
}

class AndroidJUnitRunnerLarge : AndroidJUnitRunner() {
  override fun onCreate(arguments: Bundle?) {
    arguments?.putString("annotation", LargeTest::class.java.canonicalName)
    super.onCreate(arguments)
  }
}

class AndroidJUnitRunnerMedium : AndroidJUnitRunner() {
  override fun onCreate(arguments: Bundle?) {
    arguments?.putString("annotation", MediumTest::class.java.canonicalName)
    super.onCreate(arguments)
  }
}

class AndroidJUnitRunnerSmall : AndroidJUnitRunner() {
  override fun onCreate(arguments: Bundle?) {
    arguments?.putString("annotation", SmallTest::class.java.canonicalName)
    super.onCreate(arguments)
  }
}

class AndroidJUnitRunnerFlaky : AndroidJUnitRunner() {
  override fun onCreate(arguments: Bundle?) {
    arguments?.putString("annotation", FlakyTest::class.java.canonicalName)
    super.onCreate(arguments)
  }
}

