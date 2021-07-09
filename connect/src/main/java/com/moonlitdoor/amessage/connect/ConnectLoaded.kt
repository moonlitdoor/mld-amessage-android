package com.moonlitdoor.amessage.connect

import android.Manifest
import androidx.camera.core.ImageProxy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionRequired
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.tasks.Task
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcode
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.moonlitdoor.amessage.connect.invited.InvitedPage
import com.moonlitdoor.amessage.connect.pending.PendingPage
import com.moonlitdoor.amessage.connect.qrcode.QRCodePage
import com.moonlitdoor.amessage.connect.scan.ScanPage
import com.moonlitdoor.amessage.domain.model.Connection
import com.moonlitdoor.amessage.extensions.Ensure
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.concurrent.Executor

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ConnectLoaded(
  state: ConnectViewState.Loaded,
  executor: Executor,
  pendingConfirm: (Connection) -> Unit,
  pendingReject: (Connection) -> Unit,
  detect: (FirebaseVisionImage) -> Task<List<FirebaseVisionBarcode>>,
  connectionFound: (Connection, ImageProxy) -> Unit,
  experimentsCodeFound: (ImageProxy) -> Unit,
  developerSettingsCodeFound: (ImageProxy) -> Unit,
  employeeSettingsCodeFound: (ImageProxy) -> Unit,
  createConnection: (Connection) -> Unit,
  enableExperiments: () -> Unit,
  enableDeveloperSettings: () -> Unit,
  enableEmployeeSettings: () -> Unit,
  cancelCurrentScan: () -> Unit,
) {
  Timber.d("ConnectLoaded")

  val cameraPermissionState = rememberPermissionState(Manifest.permission.CAMERA)
  PermissionRequired(
    permissionState = cameraPermissionState,
    permissionNotGrantedContent = {
      ConnectLoaded(
        tabs = listOf(
          ConnectTabs.Pending,
          ConnectTabs.Invited,
          ConnectTabs.QRCode
        ),
        state = state,
        executor = executor,
        pendingConfirm = pendingConfirm,
        pendingReject = pendingReject,
        detect = detect,
        connectionFound = connectionFound,
        experimentsCodeFound = experimentsCodeFound,
        developerSettingsCodeFound = developerSettingsCodeFound,
        employeeSettingsCodeFound = employeeSettingsCodeFound,
        createConnection = createConnection,
        enableExperiments = enableExperiments,
        enableDeveloperSettings = enableDeveloperSettings,
        enableEmployeeSettings = enableEmployeeSettings,
        cancelCurrentScan = cancelCurrentScan,
      )
      SideEffect {
        cameraPermissionState.launchPermissionRequest()
      }
    },
    permissionNotAvailableContent = {
      ConnectLoaded(
        tabs = listOf(
          ConnectTabs.Pending,
          ConnectTabs.Invited,
          ConnectTabs.QRCode
        ),
        state = state,
        executor = executor,
        pendingConfirm = pendingConfirm,
        pendingReject = pendingReject,
        detect = detect,
        connectionFound = connectionFound,
        experimentsCodeFound = experimentsCodeFound,
        developerSettingsCodeFound = developerSettingsCodeFound,
        employeeSettingsCodeFound = employeeSettingsCodeFound,
        createConnection = createConnection,
        enableExperiments = enableExperiments,
        enableDeveloperSettings = enableDeveloperSettings,
        enableEmployeeSettings = enableEmployeeSettings,
        cancelCurrentScan = cancelCurrentScan,
      )
    }
  ) {
    ConnectLoaded(
      tabs = listOf(
        ConnectTabs.Pending,
        ConnectTabs.Invited,
        ConnectTabs.QRCode,
        ConnectTabs.Scan
      ),
      state = state,
      executor = executor,
      pendingConfirm = pendingConfirm,
      pendingReject = pendingReject,
      detect = detect,
      connectionFound = connectionFound,
      experimentsCodeFound = experimentsCodeFound,
      developerSettingsCodeFound = developerSettingsCodeFound,
      employeeSettingsCodeFound = employeeSettingsCodeFound,
      createConnection = createConnection,
      enableExperiments = enableExperiments,
      enableDeveloperSettings = enableDeveloperSettings,
      enableEmployeeSettings = enableEmployeeSettings,
      cancelCurrentScan = cancelCurrentScan,
    )
  }
}

@OptIn(ExperimentalPagerApi::class, ExperimentalPermissionsApi::class)
@Composable
private fun ConnectLoaded(
  tabs: List<ConnectTabs>,
  state: ConnectViewState.Loaded,
  executor: Executor,
  pendingConfirm: (Connection) -> Unit,
  pendingReject: (Connection) -> Unit,
  detect: (FirebaseVisionImage) -> Task<List<FirebaseVisionBarcode>>,
  connectionFound: (Connection, ImageProxy) -> Unit,
  experimentsCodeFound: (ImageProxy) -> Unit,
  developerSettingsCodeFound: (ImageProxy) -> Unit,
  employeeSettingsCodeFound: (ImageProxy) -> Unit,
  createConnection: (Connection) -> Unit,
  enableExperiments: () -> Unit,
  enableDeveloperSettings: () -> Unit,
  enableEmployeeSettings: () -> Unit,
  cancelCurrentScan: () -> Unit,
) {
  Timber.d("ConnectLoaded")

  Scaffold(
    topBar = {
      TopAppBar(
        title = {
          Text(text = stringResource(id = R.string.connect_title))
        },
        elevation = 12.dp,
      )
    },
  ) {
    Column(Modifier.fillMaxSize()) {
      val coroutineScope = rememberCoroutineScope()
      val pagerState = rememberPagerState(pageCount = tabs.size)
      Surface(elevation = 12.dp) {
        TabRow(
          selectedTabIndex = pagerState.currentPage,
          indicator = { tabPositions ->
            TabRowDefaults.Indicator(
              Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
            )
          }
        ) {
          tabs.forEachIndexed { index, item ->
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
      if (tabs.isNotEmpty()) {
        HorizontalPager(
          state = pagerState,
          modifier = Modifier
            .weight(1f)
            .fillMaxWidth(),
        ) { index ->
          Box(modifier = Modifier.fillMaxSize()) {
            Ensure exhaustive when (tabs[index]) {
              is ConnectTabs.Pending -> PendingPage(
                state = state.pending,
                confirm = pendingConfirm,
                reject = pendingReject,
              )
              is ConnectTabs.Invited -> InvitedPage(state = state.invited)
              is ConnectTabs.QRCode -> QRCodePage(state = state.qr)
              is ConnectTabs.Scan -> ScanPage(
                state = state.scan,
                isCurrentPage = tabs[pagerState.currentPage] == ConnectTabs.Scan,
                executor = executor,
                detect = detect,
                connectionFound = connectionFound,
                experimentsCodeFound = experimentsCodeFound,
                developerSettingsCodeFound = developerSettingsCodeFound,
                employeeSettingsCodeFound = employeeSettingsCodeFound,
                createConnection = createConnection,
                enableExperiments = enableExperiments,
                enableDeveloperSettings = enableDeveloperSettings,
                enableEmployeeSettings = enableEmployeeSettings,
                cancelCurrentScan = cancelCurrentScan,
              )
            }
          }
        }
      }
    }
  }
}
