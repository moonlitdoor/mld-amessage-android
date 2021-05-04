package com.moonlitdoor.amessage.faq

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.moonlitdoor.amessage.domain.model.FrequentlyAskedQuestion

@Composable
fun FaqResult(result: FaqViewState.Result) {
  LazyColumn {
    itemsIndexed(result.items) { index, item ->
      FaqItem(index = index, faq = item)
      Divider()
    }
  }
}

@Preview(showBackground = true)
@Composable
fun FaqResultPreview() {
  FaqResult(
    result = FaqViewState.Result(
      listOf(
        FrequentlyAskedQuestion(
          id = 0,
          rank = 1,
          question = "Is this the question?",
          answer = "Why yes it is the answer to a question",
          visible = true,
        ),
        FrequentlyAskedQuestion(
          id = 1,
          rank = 2,
          answer = "Is this the question?",
          question = "Why yes it is the answer to a question",
          visible = true,
        )
      )
    )
  )
}
