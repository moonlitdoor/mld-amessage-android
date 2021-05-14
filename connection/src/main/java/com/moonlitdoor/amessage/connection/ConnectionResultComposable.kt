package com.moonlitdoor.amessage.connection

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.moonlitdoor.amessage.components.AppChrome
import com.moonlitdoor.amessage.components.Navigation
import com.moonlitdoor.amessage.domain.model.AssociatedData
import com.moonlitdoor.amessage.domain.model.ConnectionWithConversations
import com.moonlitdoor.amessage.domain.model.Handle
import com.moonlitdoor.amessage.domain.model.Id
import com.moonlitdoor.amessage.domain.model.Keys
import com.moonlitdoor.amessage.domain.model.Token
import timber.log.Timber
import java.time.Instant

@Composable
fun ConnectionResult(navHostController: NavHostController, viewModel: ConnectionViewModel, state: ConnectionViewState.Result, setAppChrome: (appChrome: AppChrome) -> Unit) {
  Timber.d("ConnectionResult Composable")
  setAppChrome(
    AppChrome(
      title = state.item.handle.value,
      navigation = Navigation { navHostController.popBackStack() },
      fab = {
        FloatingActionButton(onClick = { viewModel.createConversation(title = "title", topic = null) }) {
          Icon(painterResource(id = R.drawable.ic_baseline_chat_24), null)
        }
      },
    )
  )
  Text(state.item.handle.value)
}

@Preview(showBackground = true)
@Composable
fun ConnectionResultPreview() {
  val navHostController = rememberNavController()
  val viewModel: ConnectionViewModel = viewModel()
  val viewState = ConnectionViewState.Result(
    ConnectionWithConversations(
      profileId = Id(),
      connectionId = Id(),
      handle = Handle("handle"),
      token = Token("token"),
      associatedData = AssociatedData(),
      keys = Keys("keys"),
      state = com.moonlitdoor.amessage.domain.model.Connection.State.Connected,
      scanned = Instant.now(),
      confirmed = Instant.now()
    )
  )
  ConnectionResult(navHostController, viewModel, viewState) {}
}
