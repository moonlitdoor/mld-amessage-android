package com.moonlitdoor.amessage.about

import android.webkit.WebView
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import kotlinx.coroutines.launch
import timber.log.Timber

@OptIn(ExperimentalMaterialApi::class, ExperimentalAnimationApi::class)
@Composable
fun AcknowledgementPage(state: AboutScreenState.Loaded) {
  Timber.d("AcknowledgementPage")

  val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
    bottomSheetState = rememberBottomSheetState(
      initialValue = BottomSheetValue.Collapsed
    )
  )
  val coroutineScope = rememberCoroutineScope()
  var titleState by remember { mutableStateOf<String?>(null) }
  var urlState by remember { mutableStateOf<String?>(null) }

  BottomSheetScaffold(
    sheetPeekHeight = 0.dp,
    scaffoldState = bottomSheetScaffoldState,
    sheetContent = {
      Scaffold(
        topBar = {
          TopAppBar(
            title = { titleState?.let { Text(text = it) } },
            actions = {
              IconButton(
                onClick = {
                  titleState = null
                  urlState = null
                  coroutineScope.launch {
                    bottomSheetScaffoldState.bottomSheetState.collapse()
                  }
                },
              ) {
                Icon(painter = painterResource(id = R.drawable.ic_baseline_close_24), contentDescription = "")
              }
            },
          )
        }
      ) {
        val scrollState = rememberScrollState()
        urlState?.let { url ->
          AndroidView(
            modifier = Modifier.verticalScroll(scrollState),
            factory = { context ->
              WebView(context).apply {
                loadUrl(
                  url.let {
                    if (!it.contains("https")) {
                      it.replace("http", "https")
                    } else it
                  }
                )
              }
            }
          )
        }
      }
    }
  ) {
    LazyColumn {
      items(state.acknowledgements) {
        AcknowledgementItem(item = it) { title, url ->
          titleState = title
          urlState = url
          coroutineScope.launch {
            bottomSheetScaffoldState.bottomSheetState.expand()
          }
        }
        Divider()
      }
    }
  }
}

@Preview(showBackground = true)
@Composable
fun AcknowledgementPagePreview() {
  MaterialTheme {
    AcknowledgementPage(
      AboutScreenState.Loaded(
        version = "",
        buildDate = "",
        privacyPolicyUrl = "",
        termsOfUseUrl = "",
        websiteUrl = "",
        acknowledgements = emptyList(),
      )
    )
  }
}
