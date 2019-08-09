plugins {
  id("com.android.library")
  kotlin("android")
  kotlin("kapt")
}

android {
  compileSdkVersion(COMPILE_SDK_VERSION)

  lintOptions {
    disable("RtlEnabled")
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

  dataBinding {
    isEnabled = true
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

  implementation(project(M.BINDINGS))
  implementation(project(M.EXTENSIONS))
  implementation(project(M.EXPERIMENTS))
  implementation(project(M.RESOURCES))

  implementation(D.androidxAppcompatAppcompat)
  implementation(D.androidxConstraintlayoutConstraintlayout)
  implementation(D.androidxLifecycleLifecycleLivedataKtx)
  implementation(D.androidxLifecycleLifecycleViewmodelKtx)
  implementation(D.androidxNavigationNavigationFragmentKtx)
  implementation(D.androidxNavigationNavigationUiKtx)
  implementation(D.androidxRecyclerviewRecyclerview)
  implementation(D.orgJetbrainsKotlinKotlinStandardLibrary)
  implementation(D.orgKoinKoinAndroid)
  implementation(D.orgKoinKoinAndroidxViewmodel)

  debugImplementation(D.androidxFragmentFragmentTesting)
  debugImplementation(D.androidxTestCore)

  testImplementation(D.androidxFragmentFragmentTesting)
  testImplementation(D.androidxTestExtJunitKtx)
  testImplementation(D.orgKoinKoinTest)
  testImplementation(D.orgRobolectricRobolectric)

  androidTestUtil(D.androidxTestOrchestrator)

  androidTestImplementation(D.androidxTestEspressoEspressoCore)
  androidTestImplementation(D.androidxFragmentFragmentTesting)
  androidTestImplementation(D.androidxTestExtJunitKtx)
  androidTestImplementation(D.orgKoinKoinTest)

}
