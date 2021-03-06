package com.moonlitdoor.amessage.connect.invited

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
import java.time.Instant

@Composable
fun InvitedLoaded(state: InvitedViewState.Loaded) {
  Timber.d("InvitedLoaded")
  LazyColumn {
    items(state.items) {
      Timber.d("$it")
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .wrapContentHeight()
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
fun InvitedResultPreview() {
  val viewState = InvitedViewState.Loaded(
    listOf(
      Connection(
        connectionId = Id(),
        profileId = Id(),
        token = Token("token1"),
        handle = Handle("handle1"),
        state = Connection.State.Invited,
        associatedData = AssociatedData(),
        keys = Keys("keys1"),
        scanned = Instant.now(),
      ),
      Connection(
        connectionId = Id(),
        profileId = Id(),
        token = Token("token2"),
        handle = Handle("handle2"),
        state = Connection.State.Invited,
        associatedData = AssociatedData(),
        keys = Keys("keys2"),
        scanned = Instant.now(),
      ),
    )
  )
  InvitedLoaded(viewState)
}
