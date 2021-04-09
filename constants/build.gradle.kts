plugins {
  id("com.moonlitdoor.amessage.android.library")
  id("kotlin-kapt")
}

dependencies {

  testImplementation(com.moonlitdoor.amessage.dependencies.Dependencies.Androidx.Test.Ext.junitKtx)
  testImplementation(com.moonlitdoor.amessage.dependencies.Dependencies.Junit.junit)
  testImplementation(com.moonlitdoor.amessage.dependencies.Dependencies.Org.Robolectric.robolectric)

  androidTestImplementation(com.moonlitdoor.amessage.dependencies.Dependencies.Androidx.Test.rules)
  androidTestImplementation(com.moonlitdoor.amessage.dependencies.Dependencies.Androidx.Test.runner)
  androidTestImplementation(com.moonlitdoor.amessage.dependencies.Dependencies.Androidx.Test.Ext.junitKtx)

}
