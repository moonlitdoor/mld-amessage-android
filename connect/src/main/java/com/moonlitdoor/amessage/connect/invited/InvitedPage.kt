package com.moonlitdoor.amessage.connect.invited

import androidx.compose.runtime.Composable
import com.moonlitdoor.amessage.components.Loading
import com.moonlitdoor.amessage.extensions.Ensure
import timber.log.Timber

@Composable
fun InvitedPage(state: InvitedViewState) {
  Timber.d("InvitedPage")
  Ensure exhaustive when (state) {
    is InvitedViewState.Loading -> Loading("Invited") { /* TODO pop the backstack */ }
    is InvitedViewState.Empty -> InvitedEmpty()
    is InvitedViewState.Loaded -> InvitedLoaded(state = state)
  }
}
