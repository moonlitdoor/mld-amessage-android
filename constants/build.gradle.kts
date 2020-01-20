plugins {
  id("com.android.library")
  kotlin("android")
  kotlin("kapt")
}

android {
  compileSdkVersion(COMPILE_SDK_VERSION)

  lintOptions {
    isWarningsAsErrors = true
    isAbortOnError = true
    xmlReport = false
  }

  testOptions {
    unitTests.apply {
      isReturnDefaultValues = true
      isIncludeAndroidResources = true
    }
    execution = TEST_ORCHESTRATOR
    animationsDisabled = true
  }

  defaultConfig {
    minSdkVersion(MIN_SDK_VERSION)
    targetSdkVersion(TARGET_SDK_VERSION)
    testInstrumentationRunner = TEST_RUNNER
    testInstrumentationRunnerArguments = TEST_RUNNER_ARGUMENTS
  }

  buildTypes {
    getByName(RELEASE) {
      isMinifyEnabled = MINIFY
//      isShrinkResources = SHRINK
      proguardFiles(getDefaultProguardFile(PROGUARD_ANDROID_FILE), PROGUARD_FILE)
    }
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }

  sourceSets {
    getByName(SOURCE_SET_TEST) {
      java.srcDir(SHARED_TEST_DIR)
    }
    getByName(SOURCE_SET_ANDROID_TEST) {
      java.srcDir(SHARED_TEST_DIR)
    }
  }

}

dependencies {

  kapt(D.Com.Google.Dagger.daggerCompiler)

  implementation(D.Com.Google.Dagger.dagger)
  implementation(D.Org.Jetbrains.Kotlin.kotlinStandardLibrary)

  testImplementation(D.Androidx.Test.Ext.junitKtx)
  testImplementation(D.Org.Robolectric.robolectric)
  testImplementation(D.Androidx.Test.core)
  testImplementation(D.Androidx.Test.Espresso.espressoCore)
  testImplementation(D.Androidx.Test.Espresso.espressoIdlingResource)
  testImplementation(D.Androidx.Test.Espresso.Idling.idlingConcurrent)
  testImplementation(D.Androidx.Test.rules)
  testImplementation(D.Androidx.Test.runner)
  testImplementation(D.Com.Google.Truth.truth)
  testImplementation(D.Junit.junit)

  androidTestUtil(D.Androidx.Test.orchestrator)

  androidTestImplementation(D.Androidx.Test.core)
  androidTestImplementation(D.Androidx.Test.Espresso.espressoCore)
  androidTestImplementation(D.Androidx.Test.Ext.junitKtx)

}
