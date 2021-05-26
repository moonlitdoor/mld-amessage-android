package com.moonlitdoor.amessage.faq

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.moonlitdoor.amessage.components.Loading
import com.moonlitdoor.amessage.extensions.Ensure
import timber.log.Timber

@Composable
fun Faq(navHostController: NavHostController, viewModel: FaqViewModel, showBottomBar: (Boolean) -> Unit) {
  Timber.d("Faq Composable")
  showBottomBar(false)
  val viewState by viewModel.viewState.collectAsState(initial = FaqViewState.Loading)
  viewState.let { state ->
    Ensure exhaustive when (state) {
      is FaqViewState.Loading -> Loading()
      is FaqViewState.Empty -> FaqEmpty()
      is FaqViewState.Result -> FaqResult(state)
    }
  }
}

@Preview
@Composable
fun FaqPreview() {
  MaterialTheme {
    val navHostController = rememberNavController()
    val viewModel: FaqViewModel = viewModel()
    Faq(navHostController, viewModel) { }
  }
}
