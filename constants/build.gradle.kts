plugins {
  id("com.moonlitdoor.amessage.android.library")
  id("kotlin-kapt")
}

dependencies {

//  implementation(project(M.RESOURCES))

  testImplementation(com.moonlitdoor.amessage.dependencies.Dependencies.Androidx.Test.Ext.junitKtx)
  testImplementation(com.moonlitdoor.amessage.dependencies.Dependencies.Junit.junit)
  testImplementation(com.moonlitdoor.amessage.dependencies.Dependencies.Org.Robolectric.robolectric)

//  androidTestUtil(D.Androidx.Test.orchestrator)

  androidTestImplementation(com.moonlitdoor.amessage.dependencies.Dependencies.Androidx.Test.rules)
  androidTestImplementation(com.moonlitdoor.amessage.dependencies.Dependencies.Androidx.Test.runner)
  androidTestImplementation(com.moonlitdoor.amessage.dependencies.Dependencies.Androidx.Test.Ext.junitKtx)

}
