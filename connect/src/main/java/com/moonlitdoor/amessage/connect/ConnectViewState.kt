package com.moonlitdoor.amessage.connect

import com.moonlitdoor.amessage.connect.invited.InvitedViewState
import com.moonlitdoor.amessage.connect.pending.PendingViewState
import com.moonlitdoor.amessage.connect.qrcode.QRCodeViewState
import com.moonlitdoor.amessage.connect.scan.ScanViewState

sealed class ConnectViewState {
  object Loading : ConnectViewState()
  data class Loaded(val pending: PendingViewState, val invited: InvitedViewState, val qr: QRCodeViewState, val scan: ScanViewState) : ConnectViewState()
}
