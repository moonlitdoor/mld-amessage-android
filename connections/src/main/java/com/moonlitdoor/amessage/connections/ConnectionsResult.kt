package com.moonlitdoor.amessage.connections

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moonlitdoor.amessage.domain.model.AssociatedData
import com.moonlitdoor.amessage.domain.model.Connection
import com.moonlitdoor.amessage.domain.model.Handle
import com.moonlitdoor.amessage.domain.model.Id
import com.moonlitdoor.amessage.domain.model.Keys
import com.moonlitdoor.amessage.domain.model.Token
import com.moonlitdoor.amessage.routes.Routes
import timber.log.Timber
import java.time.Instant

@Composable
fun ConnectionsResult(screenState: ConnectionsScreenState.Result, navigate: (Routes) -> Unit) {
  Timber.d("ConnectionsResult Composable")
  Scaffold(
    topBar = {
      TopAppBar(
        title = {
          Text(text = stringResource(id = R.string.connections_title))
        },
        elevation = 12.dp,
      )
    },
  ) {
    LazyColumn {
      items(screenState.items) {
        Box(
          modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable {
              navigate(Routes.Connection(it.connectionId.value))
            }
            .padding(8.dp)
        ) {
          Text(text = it.handle.value, Modifier.padding(8.dp))
        }
      }
    }
  }
}

@Preview
@Composable
fun ConnectionsResultPreview() {
  val viewState = ConnectionsScreenState.Result(
    listOf(
      Connection(
        connectionId = Id(),
        profileId = Id(),
        token = Token("token"),
        handle = Handle("handle"),
        state = Connection.State.Connected,
        keys = Keys("keys"),
        associatedData = AssociatedData(),
        scanned = Instant.now(),
      )
    )
  )
  ConnectionsResult(viewState) {}
}
