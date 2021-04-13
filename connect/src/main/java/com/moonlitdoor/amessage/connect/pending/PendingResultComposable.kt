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
import com.moonlitdoor.amessage.domain.model.AssociatedData
import com.moonlitdoor.amessage.domain.model.Connection
import com.moonlitdoor.amessage.domain.model.Handle
import com.moonlitdoor.amessage.domain.model.Id
import com.moonlitdoor.amessage.domain.model.Keys
import com.moonlitdoor.amessage.domain.model.Token
import timber.log.Timber

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
        Text(text = it.handle.value, Modifier.padding(8.dp))
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
        connectionId = Id(),
        token = Token("token"),
        handle = Handle("handle"),
        state = Connection.State.Connected,
        associatedData = AssociatedData(),
        keys = Keys("keys")
      )
    )
  )
  PendingResult(viewState)
}
