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

  implementation(D.orgJetbrainsKotlinKotlinStandardLibrary)
  implementation(D.comGoogleFirebaseFirebaseCore)

  testImplementation(D.androidxTestExtJunitKtx)
  testImplementation(D.orgRobolectricRobolectric)

  androidTestUtil(D.androidxTestOrchestrator)

  androidTestImplementation(D.androidxTestEspressoEspressoCore)
  androidTestImplementation(D.androidxTestExtJunitKtx)
}
