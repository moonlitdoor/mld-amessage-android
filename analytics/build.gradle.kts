import com.moonlitdoor.amessage.dependencies.Dependencies

plugins {
  id("com.moonlitdoor.amessage.android.library")
}

dependencies {

  implementation(Dependencies.Com.Google.Firebase.firebaseAnalytics)
//  implementation(Dependencies.Com.Google.Firebase.firebaseIid)

  testImplementation(Dependencies.Androidx.Test.Ext.junitKtx)
  testImplementation(Dependencies.Junit.junit)
  testImplementation(Dependencies.Org.Robolectric.robolectric)

//  androidTestUtil(D.Androidx.Test.orchestrator)

  androidTestImplementation(Dependencies.Androidx.Test.rules)
  androidTestImplementation(Dependencies.Androidx.Test.runner)
  androidTestImplementation(Dependencies.Androidx.Test.Ext.junitKtx)
}
