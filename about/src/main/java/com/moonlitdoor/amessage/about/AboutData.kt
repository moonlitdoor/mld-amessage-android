package com.moonlitdoor.amessage.about

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import com.moonlitdoor.amessage.extensions.Ensure
import kotlinx.coroutines.launch
import timber.log.Timber

@Composable
@OptIn(ExperimentalPagerApi::class)
fun AboutData(state: AboutScreenState.Data, popBackStack: () -> Unit) {
  Timber.d("AboutData")
  Scaffold(
    topBar = {
      TopAppBar(
        title = {
          Text(text = stringResource(id = R.string.about_title))
        },
        elevation = 12.dp,
        navigationIcon = {
          IconButton(onClick = popBackStack) {
            Icon(
              imageVector = Icons.Filled.ArrowBack,
              contentDescription = stringResource(R.string.connect_ok)
            )
          }
        },
      )
    },
  ) {
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = AboutPages.values().size)
    TabRow(
      selectedTabIndex = pagerState.currentPage,
      indicator = { tabPositions ->
        TabRowDefaults.Indicator(
          Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
        )
      }
    ) {
      AboutPages.values().forEachIndexed { index, page ->
        Tab(
          text = { Text(text = stringResource(id = page.title)) },
          selected = pagerState.currentPage == index,
          onClick = { coroutineScope.launch { pagerState.scrollToPage(index) } },
        )
      }
    }
    HorizontalPager(state = pagerState) { page ->
      Ensure exhaustive when (AboutPages.values()[page]) {
        AboutPages.STATISTICS -> StatisticsPage(state = state)
        AboutPages.ACKNOWLEDGEMENTS -> AcknowledgementPage(state = state)
      }
    }
  }
}

@Preview(showBackground = true)
@Composable
fun AboutData() {
  MaterialTheme {
    AboutData(
      AboutScreenState.Data(
        version = "version",
        buildDate = "buildDate",
        acknowledgements = emptyList()
      )
    ) {}
  }
}
