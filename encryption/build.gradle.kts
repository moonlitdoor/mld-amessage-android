import com.moonlitdoor.amessage.dependencies.Dependencies

plugins {
  id("com.moonlitdoor.amessage.android.library")
}

android {
  testOptions {
    unitTests.apply {
      isIncludeAndroidResources = true
    }
  }
}

dependencies {

  api("com.google.crypto.tink:tink-android:1.5.0")

  testImplementation(Dependencies.Androidx.Test.Ext.junitKtx)
  testImplementation(Dependencies.Androidx.Test.rules)
  testImplementation(Dependencies.Androidx.Test.runner)
  testImplementation(Dependencies.Junit.junit)
  testImplementation(Dependencies.Org.Robolectric.robolectric)

//  androidTestUtil(D.Androidx.Test.orchestrator)

  androidTestImplementation(Dependencies.Androidx.Test.rules)
  androidTestImplementation(Dependencies.Androidx.Test.runner)
  androidTestImplementation(Dependencies.Androidx.Test.Ext.junitKtx)

}

