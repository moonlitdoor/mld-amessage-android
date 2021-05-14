package com.moonlitdoor.amessage.connect.pending

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.moonlitdoor.amessage.components.Loading
import com.moonlitdoor.amessage.connect.ConnectViewModel
import com.moonlitdoor.amessage.extensions.Ensure
import timber.log.Timber

@Composable
fun Pending(viewModel: ConnectViewModel) {
  Timber.d("Pending Composable")
  val viewState by viewModel.pendingViewState.collectAsState(initial = PendingViewState.Loading)
  Ensure exhaustive when (viewState) {
    is PendingViewState.Loading -> Loading()
    is PendingViewState.Empty -> PendingEmpty()
    is PendingViewState.Result -> PendingResult(
      viewModel = viewModel,
      viewState = viewState as PendingViewState.Result
    )
  }
}

@Preview(showBackground = false)
@Composable
fun PendingPreview() {
  MaterialTheme {
    val viewModel: ConnectViewModel = viewModel()
    Pending(viewModel)
  }
}
