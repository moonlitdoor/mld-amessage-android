import com.moonlitdoor.amessage.dependencies.Dependencies

plugins {
  id("com.moonlitdoor.amessage.android.library")
}

dependencies {

  implementation(Dependencies.Com.JakeWharton.Timber.timber)

  testImplementation(Dependencies.Androidx.Test.Ext.junitKtx)
  testImplementation(Dependencies.Junit.junit)
  testImplementation(Dependencies.Org.Robolectric.robolectric)

  androidTestUtil(Dependencies.Androidx.Test.orchestrator)

  androidTestImplementation(Dependencies.Androidx.Test.rules)
  androidTestImplementation(Dependencies.Androidx.Test.runner)
  androidTestImplementation(Dependencies.Androidx.Test.Ext.junitKtx)

}
