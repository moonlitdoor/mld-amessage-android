plugins {
  id("com.android.library")
  kotlin("android")
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

//  TODO
//  sourceSets {
//    getByName(SOURCE_SET_TEST) {
//      java.srcDir(SHARED_TEST_DIR)
//    }
//    getByName(SOURCE_SET_ANDROID_TEST) {
//      java.srcDir(SHARED_TEST_DIR)
//    }
//  }

}

dependencies {

  implementation(project(M.ENCRYPTION))

  implementation(D.Com.JakeWharton.Timber.timber)
  implementation(D.Com.SquareUp.OkHttp3.okhttp)
  implementation(D.Com.SquareUp.OkHttp3.loggingInterceptor)
  implementation(D.Com.SquareUp.Retrofit2.converterGson)
  implementation(D.Com.SquareUp.Retrofit2.retrofit)
  implementation(D.Org.Jetbrains.Kotlin.kotlinStandardLibrary)
  implementation(D.Org.Koin.koinAndroid)

  testImplementation(D.Androidx.Test.Ext.junitKtx)
  testImplementation(D.Com.SquareUp.OkHttp3.mockWebServer)
  testImplementation(D.Org.Koin.koinTest)
  testImplementation(D.Org.Robolectric.robolectric)
  testImplementation(D.Org.Jetbrains.Kotlinx.kotlinxCoroutinesAndroid)
  testImplementation(D.Org.Jetbrains.Kotlinx.kotlinxCoroutinesCore)

  androidTestUtil(D.Androidx.Test.orchestrator)

  androidTestImplementation(D.Org.Koin.koinTest)
  androidTestImplementation(D.Com.SquareUp.OkHttp3.mockWebServer)
  androidTestImplementation(D.Androidx.Test.Espresso.espressoCore)
  androidTestImplementation(D.Androidx.Test.Ext.junitKtx)
  androidTestImplementation(D.Org.Jetbrains.Kotlinx.kotlinxCoroutinesAndroid)
  androidTestImplementation(D.Org.Jetbrains.Kotlinx.kotlinxCoroutinesCore)

}
