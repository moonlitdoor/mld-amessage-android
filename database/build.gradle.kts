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
    javaCompileOptions {
      annotationProcessorOptions {
        arguments = mapOf("room.schemaLocation" to "$projectDir/schemas")
      }
    }
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

  kapt(D.androidxRoomRoomCompiler)

  implementation(project(M.CONSTANTS))
  implementation(D.androidxLifecycleLifecycleLivedataKtx)
  implementation(D.androidxRoomRoomCoroutines)
  implementation(D.androidxRoomRoomRuntime)
  implementation(D.comMoonlitdoorSharedPreferenceLiveDataSharedPreferenceLiveData)
  implementation(D.orgJetbrainsKotlinKotlinStandardLibrary)
  implementation(D.orgKoinKoinAndroid)

  debugImplementation(D.comAmitshekharAndroidDebugDb)

  testImplementation(D.androidxTestExtJunitKtx)
  testImplementation(D.orgKoinKoinTest)
  testImplementation(D.orgRobolectricRobolectric)

  androidTestUtil(D.androidxTestOrchestrator)

  androidTestImplementation(D.orgKoinKoinTest)
  androidTestImplementation(D.androidxTestEspressoEspressoCore)
  androidTestImplementation(D.androidxTestExtJunitKtx)

}
