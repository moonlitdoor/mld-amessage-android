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
