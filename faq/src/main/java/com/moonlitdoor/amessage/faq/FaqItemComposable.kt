package com.moonlitdoor.amessage.faq

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moonlitdoor.amessage.components.ExpandableContent
import com.moonlitdoor.amessage.domain.model.FrequentlyAskedQuestion

@Composable
fun FaqItem(index: Int, faq: FrequentlyAskedQuestion, expanded: Boolean = false) {
  var expandedState by remember { mutableStateOf(expanded) }
  Column {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .clickable(onClick = { expandedState = expandedState.not() })
        .padding(16.dp),
    ) {
      Text(
        text = "$index.",
        style = MaterialTheme.typography.subtitle1,
        modifier = Modifier.padding(end = 4.dp)
      )
      Text(
        text = faq.question,
        style = MaterialTheme.typography.subtitle1,
      )
    }
    ExpandableContent(visible = expandedState, initialVisibility = expandedState) {
      Column(modifier = Modifier.padding(start = 20.dp, end = 20.dp, bottom = 16.dp)) {
        Text(
          text = faq.answer,
        )
      }
    }
  }
}

@Preview(showBackground = true)
@Composable
fun FaqItemPreview() {
  Column {
    FaqItem(
      1,
      FrequentlyAskedQuestion(
        id = 0L,
        rank = 1L,
        question = "This is a question?",
        answer = "This is the answer.",
        visible = true
      ),
      expanded = true
    )
    Divider()
    FaqItem(
      2,
      FrequentlyAskedQuestion(
        id = 0L,
        rank = 1L,
        question = "This is a question?",
        answer = "This is the answer.",
        visible = true
      )
    )
  }
}
