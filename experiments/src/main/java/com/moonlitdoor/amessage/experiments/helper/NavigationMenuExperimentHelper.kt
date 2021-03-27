package com.moonlitdoor.amessage.experiments.helper

import android.view.Menu

//import com.moonlitdoor.amessage.resources.R


object NavigationMenuExperimentHelper {

  fun help(menu: Menu) {
    for (i in 0 until menu.size()) {
      menu.getItem(i).also { item ->
//        when (item.itemId) {
//          R.id.windows_fragment -> item.isVisible = when (Experiments.FEATURE_WINDOWS.value) {
//            Experiment.BOOLEAN.TRUE -> true
//            Experiment.BOOLEAN.FALSE -> false
//          }
//          R.id.settings -> item.isVisible = when (Experiments.FEATURE_SETTINGS.value) {
//            Experiment.BOOLEAN.TRUE -> true
//            Experiment.BOOLEAN.FALSE -> false
//          }
//          R.id.help_fragment -> item.isVisible = when (Experiments.FEATURE_HELP.value) {
//            Experiment.BOOLEAN.TRUE -> true
//            Experiment.BOOLEAN.FALSE -> false
//          }
//          R.id.feedback_fragment -> item.isVisible = when (Experiments.FEATURE_FEEDBACK.value) {
//            Experiment.BOOLEAN.TRUE -> true
//            Experiment.BOOLEAN.FALSE -> false
//          }
//          R.id.about_fragment -> item.isVisible = when (Experiments.FEATURE_ABOUT.value) {
//            Experiment.BOOLEAN.TRUE -> true
//            Experiment.BOOLEAN.FALSE -> false
//          }
//          R.id.navigation_whats_new -> item.isVisible = when (Experiments.FEATURE_WHATS_NEW.value) {
//            Experiment.BOOLEAN.TRUE -> true
//            Experiment.BOOLEAN.FALSE -> false
//          }
//        }
      }
    }
  }
}