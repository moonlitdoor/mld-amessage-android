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
  }

  buildTypes {
    getByName(RELEASE) {
      isMinifyEnabled = MINIFY
//      isShrinkResources = SHRINK
      buildConfigField("String", "BASE_URL", "\"https://amessage.moonlitdoor.com\"")
      proguardFiles(getDefaultProguardFile(PROGUARD_ANDROID_FILE), PROGUARD_FILE)
    }
    getByName(DEBUG) {
      buildConfigField("String", "BASE_URL", "\"https://beta.amessage.moonlitdoor.com\"")
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

  implementation(project(M.ENCRYPTION))

  implementation(D.Com.Google.Dagger.dagger)
  implementation(D.Com.JakeWharton.Timber.timber)
  implementation(D.Com.SquareUp.OkHttp3.okhttp)
  api(D.Com.SquareUp.OkHttp3.loggingInterceptor)
  implementation(D.Com.SquareUp.Retrofit2.converterGson)
  api(D.Com.SquareUp.Retrofit2.retrofit)
  implementation(D.Org.Jetbrains.Kotlin.kotlinStandardLibrary)

  kaptTest(D.Com.Google.Dagger.daggerCompiler)

  testImplementation(D.Androidx.Test.Ext.junitKtx)
  testImplementation(D.Com.SquareUp.OkHttp3.mockWebServer)
  testImplementation(D.Org.Robolectric.robolectric)
  testImplementation(D.Org.Jetbrains.Kotlinx.kotlinxCoroutinesAndroid)
  testImplementation(D.Org.Jetbrains.Kotlinx.kotlinxCoroutinesCore)

  androidTestUtil(D.Androidx.Test.orchestrator)

  androidTestImplementation(D.Com.SquareUp.OkHttp3.mockWebServer)
  androidTestImplementation(D.Androidx.Test.Espresso.espressoCore)
  androidTestImplementation(D.Androidx.Test.Ext.junitKtx)
  androidTestImplementation(D.Org.Jetbrains.Kotlinx.kotlinxCoroutinesAndroid)
  androidTestImplementation(D.Org.Jetbrains.Kotlinx.kotlinxCoroutinesCore)

}
