package com.moonlitdoor.amessage.experiments.ui

//import androidx.compose.Composable
//import androidx.ui.core.Text
//import androidx.ui.foundation.VerticalScroller
//import androidx.ui.graphics.Color
//import androidx.ui.layout.Column
//import androidx.ui.layout.FlexColumn
//import androidx.ui.material.Divider
//import androidx.ui.material.TopAppBar
//import androidx.ui.tooling.preview.Preview
//import com.moonlitdoor.amessage.experiments.Experiment
//import com.moonlitdoor.amessage.experiments.Experiments
//
//@Composable
//fun ExperimentsList(experiments: List<Experiment<*>>) {
//
//  FlexColumn {
//    inflexible {
//      TopAppBar(
//        title = { Text("Experiments") }
//      )
//    }
//    expanded(1F) {
//      VerticalScroller {
//        Column {
//          experiments.forEach {
//            val open = OpenState()
//            ExperimentItem(it, open)
//            Divider(color = Color.LightGray)
//          }
//        }
//      }
//    }
//  }
//}
//
//@Preview
//@Composable
//fun DefaultPreview2() {
//  ExperimentsList(listOf(Experiments.TEST2, Experiments.TEST3, Experiments.TEST4))
//}