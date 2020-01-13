package com.moonlitdoor.amessage.experiments.ui

import androidx.compose.Composable
import androidx.compose.Model
import androidx.ui.core.*
import androidx.ui.foundation.shape.DrawShape
import androidx.ui.foundation.shape.RectangleShape
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.layout.Column
import androidx.ui.layout.Container
import androidx.ui.layout.Padding
import androidx.ui.layout.Row
import androidx.ui.material.Button
import androidx.ui.material.TextButtonStyle
import androidx.ui.tooling.preview.Preview
import com.moonlitdoor.amessage.experiments.Experiment

@Model
class OpenState(var open: Boolean = false)

@Composable
fun ExperimentItem(experiment: Experiment<*>, openState: OpenState) {
  Padding(left = 16.dp, right = 16.dp, top = 8.dp, bottom = 8.dp) {
    Row {
      Column {
        experiment.title?.let { Text(it) }
        experiment.description?.let { Text(it) }
        Text("Key: ${experiment.key}")
        Text("Remote Value: ${experiment.remoteValue}")
      }
      Button(
        text = experiment.localValue,
        style = TextButtonStyle(),
        onClick = { openState.open = !openState.open })
      if (openState.open) {
        DropdownPopup(
          dropDownAlignment = DropDownAlignment.Left,
          popupProperties = PopupProperties(
            isFocusable = true,
            onDismissRequest = { openState.open = !openState.open })
        ) {
          DrawShadow(shape = RoundedCornerShape(8.dp), elevation = 12.dp)
          Padding(padding = 6.dp) {
            Container {
              DrawShape(RectangleShape, Color.White)
              Column {
                experiment.options.forEach {
                  Padding(padding = 8.dp) {
                    Button(
                      text = it,
                      style = TextButtonStyle(),
                      onClick = {
                        experiment.localValue = it
                        openState.open = !openState.open
                      })
                  }
                }
              }
            }
          }
        }
      }
    }
  }

}

@Preview
@Composable
fun DefaultPreviewTest2() {
  val open = OpenState()
  ExperimentItem(com.moonlitdoor.amessage.experiments.Experiments.TEST2, open)
}

//@Preview
//@Composable
//fun DefaultPreviewTest4() {
//  val open = OpenState()
//  ExperimentItem(com.moonlitdoor.amessage.experiments.Experiments.TEST4, open)
//}
//
//@Preview
//@Composable
//fun DefaultPreviewTest5() {
//  val open = OpenState()
//  ExperimentItem(com.moonlitdoor.amessage.experiments.Experiments.TEST5, open)
//}
//
//@Preview
//@Composable
//fun DefaultPreviewTest6() {
//  val open = OpenState()
//  ExperimentItem(com.moonlitdoor.amessage.experiments.Experiments.TEST6, open)
//}
