plugins {
  id("com.android.library")
  kotlin("android")
  kotlin("kapt")
  id("com.moonlitdoor.jacoco")
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
    javaCompileOptions {
      annotationProcessorOptions {
        arguments = mapOf("room.schemaLocation" to "$projectDir/schemas", "room.incremental" to "true")
      }
    }
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
  kapt(D.Androidx.Room.roomCompiler)

  api(project(M.CONSTANTS))

  api("com.google.firebase:firebase-database-ktx:19.2.1")
  implementation(D.Com.Google.Dagger.dagger)
  implementation(D.Androidx.Lifecycle.lifecycleLivedataKtx)
  implementation(D.Androidx.Room.roomKtx)
  implementation(D.Androidx.Room.roomRuntime)
  implementation(D.Androidx.Core.coreKtx)
  implementation(D.Com.Moonlitdoor.SharedPreferenceLiveData.sharedPreferenceLiveData)
  implementation(D.Org.Jetbrains.Kotlin.kotlinStandardLibrary)
  implementation(D.Org.Jetbrains.Kotlinx.kotlinxCoroutinesPlayServices)
  implementation(D.Com.JakeWharton.Timber.timber)

  debugImplementation(T.Com.Amitshekhar.Android.debugDb)

  testImplementation(D.Androidx.Test.Ext.junitKtx)
  testImplementation(D.Org.Robolectric.robolectric)

  androidTestUtil(D.Androidx.Test.orchestrator)

  androidTestImplementation(D.Androidx.Test.Espresso.espressoCore)
  androidTestImplementation(D.Androidx.Test.Ext.junitKtx)

}
