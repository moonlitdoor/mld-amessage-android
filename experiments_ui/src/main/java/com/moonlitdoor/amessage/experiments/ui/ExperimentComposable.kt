package com.moonlitdoor.amessage.experiments.ui

import androidx.compose.Composable
import androidx.ui.core.Text
import androidx.ui.layout.Column
import androidx.ui.layout.Row
import androidx.ui.tooling.preview.Preview

@Composable
fun Experiment() {
  Row {
    Column {
      Text("A day in Shark Fin Cove")
      Text("Davenport, California")
      Text("December 2018")
    }
  }

}

@Preview
@Composable
fun DefaultPreview() {
  Experiment()
}