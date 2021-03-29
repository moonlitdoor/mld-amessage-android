import com.moonlitdoor.amessage.dependencies.Dependencies

plugins {
  id("com.moonlitdoor.amessage.android.library")
}

dependencies {

//  TODO Bring in Tink: https://github.com/google/tink

  testImplementation(Dependencies.Androidx.Test.Ext.junitKtx)
  testImplementation(Dependencies.Junit.junit)
  testImplementation(Dependencies.Org.Robolectric.robolectric)

//  androidTestUtil(D.Androidx.Test.orchestrator)

  androidTestImplementation(Dependencies.Androidx.Test.rules)
  androidTestImplementation(Dependencies.Androidx.Test.runner)
  androidTestImplementation(Dependencies.Androidx.Test.Ext.junitKtx)

}

