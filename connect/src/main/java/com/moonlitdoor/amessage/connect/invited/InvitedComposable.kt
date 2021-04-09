package com.moonlitdoor.amessage.connect.invited

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.moonlitdoor.amessage.components.Loading
import com.moonlitdoor.amessage.connect.ConnectViewModel
import com.moonlitdoor.amessage.extensions.Ensure

@Composable
fun Invited(viewModel: ConnectViewModel) {
  val viewState by viewModel.invitedViewState.collectAsState(initial = InvitedViewState.Loading)
  Ensure exhaustive when (viewState) {
    is InvitedViewState.Loading -> Loading()
    is InvitedViewState.Empty -> InvitedEmpty()
    is InvitedViewState.Result -> InvitedResult(viewState = viewState as InvitedViewState.Result)
  }
}

@Preview(showBackground = false)
@Composable
fun InvitedPreview() {
  MaterialTheme {
    val viewModel: ConnectViewModel = viewModel()
    Invited(viewModel)
  }
}
