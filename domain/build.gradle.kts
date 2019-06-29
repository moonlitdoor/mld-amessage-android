plugins {
  id("com.android.library")
  kotlin("android")
}

android {
  compileSdkVersion(28)

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

  implementation(project(M.CONSTANTS))
  implementation(project(M.DATABASE))
  implementation(project(M.EXTENSIONS))
  implementation(project(M.NETWORK))

  implementation(D.androidxAppcompatAppcompat)
  implementation(D.comJakeWhartonTimberTimber)
  implementation(D.orgJetbrainsKotlinKotlinStandardLibrary)
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
