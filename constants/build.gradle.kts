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
//      proguardFile(getDefaultProguardFile("proguard-android-optimize.txt"))
//      proguardFile(file("proguard-rules.pro"))
//      consumerProguardFile("consumer-rules.pro")
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

  implementation(D.orgJetbrainsKotlinKotlinStandardLibrary)
  implementation(D.orgKoinKoinAndroid)

  testImplementation(D.androidxTestExtJunitKtx)
  testImplementation(D.orgKoinKoinTest)
  testImplementation(D.orgRobolectricRobolectric)
  testImplementation(D.androidxTestCore)
  testImplementation(D.androidxTestEspressoEspressoCore)
  testImplementation(D.androidxTestEspressoEspressoIdlingResource)
  testImplementation(D.androidxTestEspressoIdlingIdlingConcurrent)
  testImplementation(D.androidxTestRules)
  testImplementation(D.androidxTestRunner)
  testImplementation(D.comGoogleTruthTruth)
  testImplementation(D.junitJunit)

  androidTestUtil(D.androidxTestOrchestrator)

  androidTestImplementation(D.androidxTestCore)
  androidTestImplementation(D.androidxTestEspressoEspressoCore)
  androidTestImplementation(D.androidxTestExtJunitKtx)
  androidTestImplementation(D.orgKoinKoinTest)
}
