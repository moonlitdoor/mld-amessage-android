package com.moonlitdoor.amessage.connect.pending

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moonlitdoor.amessage.domain.model.Connection
import timber.log.Timber
import java.util.*

@Composable
fun PendingResult(viewState: PendingViewState.Result) {
  LazyColumn {
    items(viewState.items) {
      Timber.d("$it")
      Box(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
//        .padding(8.dp)
        .clickable {
        }
      ) {
        Text(text = it.handle, Modifier.padding(8.dp))
      }
    }
  }
}

@Preview(showSystemUi = true)
@Composable
fun ConnectionsResultPreview() {
  val viewState = PendingViewState.Result(
    listOf(
      Connection(
        id = 0L,
        connectionId = UUID.randomUUID(),
        password = UUID.randomUUID(),
        salt = UUID.randomUUID(),
        token = "token",
        handle = "handle",
        state = Connection.State.Connected
      )
    )
  )
  PendingResult(viewState)
}
