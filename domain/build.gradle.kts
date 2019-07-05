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

  implementation(project(M.CONSTANTS))
  implementation(project(M.DATABASE))
  implementation(project(M.EXTENSIONS))
  implementation(project(M.NETWORK))

  implementation(D.androidxAppcompatAppcompat)
  implementation(D.comJakeWhartonTimberTimber)
  implementation(D.orgJetbrainsKotlinKotlinStandardLibrary)
  implementation(D.orgJetbrainsKotlinxKotlinxCoroutinesAndroid)
  implementation(D.orgJetbrainsKotlinxKotlinxCoroutinesCore)
  implementation(D.orgKoinKoinAndroid)
  implementation(D.orgKoinKoinAndroidxScope)
  implementation(D.orgKoinKoinAndroidxViewmodel)
  implementation(D.comGoogleFirebaseFirebaseCore)
  implementation(D.comGoogleFirebaseFirebaseMessaging)

  testImplementation(D.androidxTestRules)
  testImplementation(D.androidxTestRunner)
  testImplementation(D.androidxTestEspressoEspressoCore)
  testImplementation(D.androidxTestExtJunitKtx)
  testImplementation(D.orgRobolectricRobolectric)
  testImplementation(D.orgKoinKoinTest)

  androidTestUtil(D.androidxTestOrchestrator)

  androidTestImplementation(D.androidxTestRunner)
  androidTestImplementation(D.androidxTestEspressoEspressoCore)
  androidTestImplementation(D.androidxTestExtJunitKtx)
  androidTestImplementation(D.orgKoinKoinTest)

}
