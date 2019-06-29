plugins {
  id("com.android.library")
  kotlin("android")
  kotlin("kapt")
}

android {
  compileSdkVersion(28)

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
    execution = "ANDROIDX_TEST_ORCHESTRATOR"
    animationsDisabled = true
  }

  defaultConfig {
    minSdkVersion(24)
    targetSdkVersion(28)
    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    testInstrumentationRunnerArguments = mapOf("clearPackageData" to "true")
  }

  buildTypes {
    getByName("release") {
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
    val sharedTestDir = "src/sharedTest/java"
    getByName("test") {
      java.srcDir(sharedTestDir)
    }
    getByName("androidTest") {
      java.srcDir(sharedTestDir)
    }
  }

}

dependencies {

  implementation(project(M.BINDINGS))
  implementation(project(M.EXTENSIONS))
  implementation(project(M.EXPERIMENTS))
  implementation(project(M.RES))

  implementation(D.androidxAppcompatAppcompat)
  implementation(D.androidxConstraintlayoutConstraintlayout)
  implementation(D.androidxLifecycleLifecycleLivedataKtx)
  implementation(D.androidxLifecycleLifecycleViemodelKtx)
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
