plugins {
  id("com.android.library")
  kotlin("android")
  id("com.moonlitdoor.jacoco")
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
      isMinifyEnabled = MINIFY
//      isShrinkResources = SHRINK
      proguardFiles(getDefaultProguardFile(PROGUARD_ANDROID_FILE), PROGUARD_FILE)
    }
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }

  buildFeatures {
    dataBinding = true
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
  implementation(project(M.CONSTANTS))
  implementation(project(M.DOMAIN))
  implementation(project(M.EXTENSIONS))
  implementation(project(M.RESOURCES))

  implementation(D.Org.Jetbrains.Kotlin.kotlinStandardLibrary)
  implementation(D.Androidx.AppCompat.appcompat)
  implementation(D.Com.Google.Android.Material.material)
  implementation(D.Com.JakeWharton.Timber.timber)
  implementation(D.Androidx.ConstraintLayout.constraintLayout)

  testImplementation(D.Androidx.Test.Ext.junitKtx)
  testImplementation(D.Org.Robolectric.robolectric)

  androidTestUtil(D.Androidx.Test.orchestrator)

  androidTestImplementation(D.Androidx.Test.Espresso.espressoCore)
  androidTestImplementation(D.Androidx.Test.Ext.junitKtx)

}
