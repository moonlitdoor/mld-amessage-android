package com.moonlitdoor.amessage.connect.pending

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moonlitdoor.amessage.connect.R
import com.moonlitdoor.amessage.domain.model.AssociatedData
import com.moonlitdoor.amessage.domain.model.Connection
import com.moonlitdoor.amessage.domain.model.Handle
import com.moonlitdoor.amessage.domain.model.Id
import com.moonlitdoor.amessage.domain.model.Keys
import com.moonlitdoor.amessage.domain.model.Token
import timber.log.Timber
import java.time.Instant

@Composable
fun PendingLoaded(state: PendingViewState.Loaded, confirm: (Connection) -> Unit, reject: (Connection) -> Unit) {
  Timber.d("PendingLoaded")
  var selectedConnection by remember { mutableStateOf<Connection?>(null) }

  LazyColumn {
    items(state.items) {
      Timber.d("$it")
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .wrapContentHeight()
          .clickable {
            selectedConnection = it
          }
          .padding(16.dp)
      ) {
        Text(text = it.handle.value, Modifier.padding(8.dp))
      }
      Divider()
    }
  }

  selectedConnection?.let {
    AlertDialog(
      onDismissRequest = {
        selectedConnection = null
      },
      title = {
        Text(text = stringResource(id = R.string.connect_title))
      },
      text = {
        Text(text = stringResource(id = R.string.connect_with_handle, it.handle.value))
      },
      confirmButton = {
        Button(
          onClick = {
            confirm(it)
            selectedConnection = null
          }
        ) {
          Text(text = stringResource(id = R.string.confirm))
        }
      },
      dismissButton = {
        Button(
          onClick = {
            reject(it)
            selectedConnection = null
          }
        ) {
          Text(text = stringResource(id = R.string.reject))
        }
      }
    )
  }
}

@Preview(showSystemUi = true)
@Composable
fun ConnectionsResultPreview() {
  val viewState = PendingViewState.Loaded(
    listOf(
      Connection(
        connectionId = Id(),
        profileId = Id(),
        token = Token("token1"),
        handle = Handle("handle1"),
        state = Connection.State.Connected,
        associatedData = AssociatedData(),
        keys = Keys("keys1"),
        scanned = Instant.now(),
      ),
      Connection(
        connectionId = Id(),
        profileId = Id(),
        token = Token("token2"),
        handle = Handle("handle2"),
        state = Connection.State.Connected,
        associatedData = AssociatedData(),
        keys = Keys("keys2"),
        scanned = Instant.now(),
      )
    )
  )
  PendingLoaded(state = viewState, confirm = {}, reject = {})
}
