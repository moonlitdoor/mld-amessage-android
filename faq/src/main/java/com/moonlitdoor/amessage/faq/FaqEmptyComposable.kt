package com.moonlitdoor.amessage.faq

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import timber.log.Timber

@Composable
fun FaqEmpty() {
  Timber.d("FaqEmpty Composable")
  Box(
    modifier = Modifier.fillMaxSize(),
    contentAlignment = Alignment.Center
  ) {
    Text(text = stringResource(id = R.string.faq_none))
  }
}
