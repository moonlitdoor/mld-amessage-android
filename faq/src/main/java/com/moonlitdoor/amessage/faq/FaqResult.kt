package com.moonlitdoor.amessage.faq

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moonlitdoor.amessage.domain.model.FrequentlyAskedQuestion
import timber.log.Timber

@Composable
fun FaqResult(result: FaqScreenState.Result, popBackStack: () -> Unit) {
  Timber.d("FaqResult")
  Scaffold(
    topBar = {
      TopAppBar(
        title = {
          Text(text = stringResource(id = R.string.faq_title))
        },
        elevation = 12.dp,
        navigationIcon = {
          IconButton(onClick = popBackStack) {
            Icon(
              imageVector = Icons.Filled.ArrowBack,
              contentDescription = stringResource(R.string.connect_ok)
            )
          }
        },
      )
    },
  ) {
    LazyColumn {
      itemsIndexed(result.items) { index, item ->
        FaqItem(index = index + 1, faq = item)
        Divider()
      }
    }
  }
}

@Preview(showBackground = true)
@Composable
fun FaqResultPreview() {
  FaqResult(
    result = FaqScreenState.Result(
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
  ) {
  }
}
