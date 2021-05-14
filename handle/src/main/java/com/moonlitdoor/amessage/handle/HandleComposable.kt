package com.moonlitdoor.amessage.handle

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.moonlitdoor.amessage.components.AppChrome
import com.moonlitdoor.amessage.domain.model.Handle
import timber.log.Timber

@Composable
fun Handle(navHostController: NavHostController, viewModel: HandleViewModel, setAppChrome: (appChrome: AppChrome) -> Unit) {
  Timber.d("Handle Composable")
  var isActionItemEnabled by remember { mutableStateOf(false) }
//  setAppChrome(
//    AppChrome(
//      title = stringResource(id = R.string.handle_title),
//      actionItems = listOf(
//        ActionItem(
//          enabled = isActionItemEnabled,
//          onClick = {
//            viewModel.saveHandle()
//          },
//          imageVector = Icons.Filled.Check,
//        )
//      )
//    )
//  )

  val isHandleSet: Boolean by viewModel.isHandleSet.collectAsState(initial = false)
  if (isHandleSet) {
    Timber.d("Handle is set, popping the backstack")
    navHostController.popBackStack()
  }

  val handle: Handle by viewModel.handle.collectAsState(initial = Handle(""))
  Box(
    modifier = Modifier
      .fillMaxSize()
      .padding(top = 16.dp)
  ) {
    val focusRequester = remember { FocusRequester() }
    OutlinedTextField(
      modifier = Modifier
        .align(Alignment.TopCenter)
        .focusRequester(focusRequester),
      value = handle.value,
      onValueChange = {
        Timber.d("Handle updated to:$it")
        isActionItemEnabled = it.isNotBlank()
        viewModel.setHandle(it)
      },
      label = { Text(stringResource(R.string.handle_title)) },
    )
    DisposableEffect(Unit) {
      focusRequester.requestFocus()
      onDispose { }
    }
  }
}

@Preview(showBackground = false)
@Composable
fun DefaultPreview() {
  MaterialTheme {
    val navHostController = rememberNavController()
    val viewModel: HandleViewModel = viewModel()
    Handle(navHostController, viewModel) { }
  }
}
