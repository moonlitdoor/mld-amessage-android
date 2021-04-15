package com.moonlitdoor.amessage.connect

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.compose.registerForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Tab
import androidx.compose.material.TabPosition
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.moonlitdoor.amessage.components.AppChrome
import com.moonlitdoor.amessage.connect.invited.Invited
import com.moonlitdoor.amessage.connect.pending.Pending
import com.moonlitdoor.amessage.connect.qrcode.QRCode
import com.moonlitdoor.amessage.connect.scan.Scan
import com.moonlitdoor.amessage.extensions.Ensure
import kotlinx.coroutines.launch

private const val PERMISSIONS_REQUEST_CODE = 1776
private val PERMISSIONS_REQUIRED = arrayOf(Manifest.permission.CAMERA)

fun hasPermissions(context: Context): Boolean = PERMISSIONS_REQUIRED.all {
  ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Connect(viewModel: ConnectViewModel, setAppChrome: (AppChrome) -> Unit) {
  setAppChrome(
    AppChrome(
      title = stringResource(id = R.string.connect_title),
      showBottomBar = true
    )
  )

  val pages = remember {
    mutableStateListOf(
      ConnectTabs.Pending,
      ConnectTabs.Invited,
      ConnectTabs.QRCode,
    )
  }

  registerForActivityResult(contract = ActivityResultContracts.RequestPermission()) { granted ->
    if (granted) {
      pages.add(ConnectTabs.Scan)
    }
  }

  if (!hasPermissions(LocalContext.current)) {
    requestPermissions(LocalContext.current as Activity, PERMISSIONS_REQUIRED, PERMISSIONS_REQUEST_CODE)
  } else {
    pages.add(ConnectTabs.Scan)
  }

  Column(Modifier.fillMaxSize()) {
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = pages.size)
    Surface(elevation = 12.dp) {
      TabRow(
        selectedTabIndex = pagerState.currentPage,
        indicator = { tabPositions ->
          TabRowDefaults.Indicator(
            Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
          )
        }
      ) {
        pages.forEachIndexed { index, item ->
          Tab(
            text = { Text(stringResource(id = item.resource)) },
            selected = pagerState.currentPage == index,
            onClick = {
              coroutineScope.launch {
                pagerState.animateScrollToPage(index)
              }
            }
          )
        }
      }
    }
    if (pages.isNotEmpty()) {
      HorizontalPager(
        state = pagerState,
        modifier = Modifier
          .weight(1f)
          .fillMaxWidth(),
      ) { index ->
        Box(modifier = Modifier.fillMaxSize()) {
          Ensure exhaustive when (pages[index]) {
            is ConnectTabs.Pending -> Pending(viewModel = viewModel)
            is ConnectTabs.Invited -> Invited(viewModel = viewModel)
            is ConnectTabs.QRCode -> QRCode(viewModel = viewModel)
            is ConnectTabs.Scan -> Scan(viewModel = viewModel, isCurrentPage = pages[pagerState.currentPage] == ConnectTabs.Scan)
          }
        }
      }
    }
  }
}

@OptIn(ExperimentalPagerApi::class)
fun Modifier.pagerTabIndicatorOffset(
  pagerState: PagerState,
  tabPositions: List<TabPosition>,
): Modifier = composed {
  val targetIndicatorOffset: Dp
  val indicatorWidth: Dp

  val currentTab = tabPositions[pagerState.currentPage]
  val nextTab = tabPositions.getOrNull(pagerState.currentPage + 1)
  if (nextTab != null) {
    // If we have a next tab, lerp between the size and offset
    targetIndicatorOffset = lerp(currentTab.left, nextTab.left, pagerState.currentPageOffset)
    indicatorWidth = lerp(currentTab.width, nextTab.width, pagerState.currentPageOffset)
  } else {
    // Otherwise we just use the current tab/page
    targetIndicatorOffset = currentTab.left
    indicatorWidth = currentTab.width
  }

  fillMaxWidth()
    .wrapContentSize(Alignment.BottomStart)
    .offset(x = targetIndicatorOffset)
    .width(indicatorWidth)
}

@Preview(showBackground = false)
@Composable
fun ConnectPreview() {
  MaterialTheme {
    val viewModel: ConnectViewModel = viewModel()
    Connect(viewModel) {}
  }
}
