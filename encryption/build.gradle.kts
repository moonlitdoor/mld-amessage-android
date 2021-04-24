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

  api(Dependencies.Com.Google.Crypto.Tink.tinkAndroid)

  testImplementation(Dependencies.Androidx.Test.Ext.junitKtx)
  testImplementation(Dependencies.Androidx.Test.rules)
  testImplementation(Dependencies.Androidx.Test.runner)
  testImplementation(Dependencies.Junit.junit)
  testImplementation(Dependencies.Org.Robolectric.robolectric)

  androidTestUtil(Dependencies.Androidx.Test.orchestrator)

  androidTestImplementation(Dependencies.Androidx.Test.rules)
  androidTestImplementation(Dependencies.Androidx.Test.runner)
  androidTestImplementation(Dependencies.Androidx.Test.Ext.junitKtx)
}
