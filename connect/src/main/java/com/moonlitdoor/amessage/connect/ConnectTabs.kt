package com.moonlitdoor.amessage.connect

import androidx.annotation.StringRes

sealed class ConnectTabs(@StringRes val resource: Int) {

  object Pending : ConnectTabs(R.string.connect_pending_title)
  object Invited : ConnectTabs(R.string.connect_invited_title)
  object QRCode : ConnectTabs(R.string.connect_qr_code_title)
  object Scan : ConnectTabs(R.string.connect_scan_title)
}
