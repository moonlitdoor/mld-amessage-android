package com.moonlitdoor.amessage.about

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AcknowledgementItem(item: Acknowledgement, onClick: (title: String, url: String) -> Unit) {
  Column(
    modifier = Modifier
      .fillMaxWidth()
      .clickable { onClick(item.title, item.url) }
      .padding(8.dp)
  ) {
    Text(text = item.name, style = MaterialTheme.typography.h6)
    item.description?.let { Text(text = it, style = MaterialTheme.typography.caption) }
    Text(text = item.coordinates, style = MaterialTheme.typography.overline)
  }
}

@Preview(showBackground = true)
@Composable
fun AcknowledgementItemPreview() {
  Box(
    modifier = Modifier
      .padding(1.dp)
      .border(
        width = 1.dp,
        color = Color.Gray
      )
  ) {
    AcknowledgementItem(
      item = Acknowledgement(
        name = "Activity Compose",
        description = "description",
        group = "androidx.activity",
        artifact = "activity",
        version = "1.1.2-alpha03",
        title = "MIT",
        url = "http://www.google.com"
      )
    ) { _, _ -> }
  }
}
