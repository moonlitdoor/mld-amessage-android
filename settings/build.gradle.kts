plugins {
  id("com.android.library")
  id("androidx.navigation.safeargs")
  kotlin("android")
  kotlin("kapt")
}

android {
  compileSdkVersion(COMPILE_SDK_VERSION)

  lintOptions {
    disable("RtlEnabled", "WrongConstant")
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

  kapt(D.Com.Google.Dagger.daggerCompiler)

  implementation(project(M.BINDINGS))
  implementation(project(M.DOMAIN))
  implementation(project(M.EXPERIMENTS))
  api(project(M.EXPERIMENTS_UI))
  implementation(project(M.EXTENSIONS))
  implementation(project(M.COMPONENTS))
  implementation(project(M.CONSTANTS))
  implementation(project(M.RESOURCES))

  implementation(D.Com.Google.Dagger.dagger)
  implementation(D.Org.Jetbrains.Kotlin.kotlinStandardLibrary)
  implementation(D.Com.Google.Android.Gms.playServicesVision)
  implementation(D.Androidx.Navigation.navigationFragmentKtx)
  implementation(D.Androidx.Navigation.navigationUiKtx)
  implementation(D.Com.Google.Zxing.core)
  implementation(D.Androidx.ConstraintLayout.constraintLayout)
  implementation(D.Androidx.Preference.preference)
  implementation(D.Androidx.Legacy.legacyPreferenceV14)

  testImplementation(D.Androidx.Test.Ext.junitKtx)
  testImplementation(D.Org.Robolectric.robolectric)

  androidTestUtil(D.Androidx.Test.orchestrator)

  androidTestImplementation(D.Androidx.Test.Espresso.espressoCore)
  androidTestImplementation(D.Androidx.Test.Ext.junitKtx)

}
