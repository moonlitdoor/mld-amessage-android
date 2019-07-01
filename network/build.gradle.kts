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
      isMinifyEnabled = false
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

  implementation(project(M.ENCRYPTION))

  implementation(D.comJakeWhartonTimberTimber)
  implementation(D.comGithubIhsanbalLoggingInterceptor)
  implementation(D.comSquareupOkhttp3Okhttp)
  implementation(D.comSquareupOkhttp3LoggingInterceptor)
  implementation(D.comSquareupRetrofit2ConverterGson)
  implementation(D.comSquareupRetrofit2Retrofit)
  implementation(D.orgJetbrainsKotlinKotlinStandardLibrary)
  implementation(D.orgKoinKoinAndroid)

  testImplementation(D.androidxTestExtJunitKtx)
  testImplementation(D.comSquareupOkhttp3MockWebServer)
  testImplementation(D.orgKoinKoinTest)
  testImplementation(D.orgRobolectricRobolectric)
  testImplementation(D.orgJetbrainsKotlinxKotlinxCoroutinesAndroid)
  testImplementation(D.orgJetbrainsKotlinxKotlinxCoroutinesCore)

  androidTestUtil(D.androidxTestOrchestrator)

  androidTestImplementation(D.orgKoinKoinTest)
  androidTestImplementation(D.comSquareupOkhttp3MockWebServer)
  androidTestImplementation(D.androidxTestEspressoEspressoCore)
  androidTestImplementation(D.androidxTestExtJunitKtx)
  androidTestImplementation(D.orgJetbrainsKotlinxKotlinxCoroutinesAndroid)
  androidTestImplementation(D.orgJetbrainsKotlinxKotlinxCoroutinesCore)

}
