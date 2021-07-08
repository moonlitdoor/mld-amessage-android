package com.moonlitdoor.amessage.connect.pending

import androidx.compose.runtime.Composable
import com.moonlitdoor.amessage.components.Loading
import com.moonlitdoor.amessage.domain.model.Connection
import com.moonlitdoor.amessage.extensions.Ensure
import timber.log.Timber

@Composable
fun PendingPage(state: PendingViewState, confirm: (Connection) -> Unit, reject: (Connection) -> Unit) {
  Timber.d("PendingPage")
  Ensure exhaustive when (state) {
    is PendingViewState.Loading -> Loading("Pending") { /* TODO pop the backstack */ }
    is PendingViewState.Empty -> PendingEmpty()
    is PendingViewState.Loaded -> PendingLoaded(
      state = state,
      confirm = confirm,
      reject = reject,
    )
  }
}
