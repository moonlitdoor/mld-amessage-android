package com.moonlitdoor.amessage.constants

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ConstantsTest {

  @Test
  fun verifyExperimentCode() {
    assertTrue(Constants.EXPERIMENTS.contains("9Sk7bEqrKrRZ0vKZhi5nvXnckc983tpyw8lfRCUPUAzSWBqxhA"))
    assertTrue(Constants.EXPERIMENTS.contains("fRCUPUAzSWBqxhAV9g53wgTbsftnAz9YPNfzZ5dZs9SUAXyygZ"))
    assertTrue(Constants.EXPERIMENTS.contains("YfZO7iEcaej2mq6ZZdMyfPlhJ4C3i1XCtYH9Sk7bEqrKrRZ0vK"))
  }

  @Test
  fun verifyDeveloperSettingsCode() {
    assertTrue(Constants.DEVELOPER_SETTINGS.contains("lHC4OjlhN2m4ScJ1yfI4xYMYA7SPO71a3Ja96ag0f1WUYqvqFK"))
    assertTrue(Constants.DEVELOPER_SETTINGS.contains("96ag0f1WUYqvqFKBYxn24CRt7N0WGrg2olHsu6TGPlthImfL3y"))
    assertTrue(Constants.DEVELOPER_SETTINGS.contains("asiV4AvdRTnwHL69SmdxDN3UUbnebp357OWlHC4OjlhN2m4ScJ"))
  }

  @Test
  fun verifyEmployeeSettingsCode() {
    assertTrue(Constants.EMPLOYEE_SETTINGS.contains("pQuAJKjIu0MuNrEN2eqlZoi4Iip7MvdACLrXUA9nZXa4alNKWI"))
    assertTrue(Constants.EMPLOYEE_SETTINGS.contains("XUA9nZXa4alNKWI9bnHxRhlxso2HEqtksj2UpBZuQxdw5pVuVI"))
    assertTrue(Constants.EMPLOYEE_SETTINGS.contains("P06mqli1A8ZpJc7pTf9wcl2xnX87JSxRzz9pQuAJKjIu0MuNrE"))
  }

}