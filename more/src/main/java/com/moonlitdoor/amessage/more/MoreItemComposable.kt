package com.moonlitdoor.amessage.more

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import timber.log.Timber

@Composable
fun MoreItem(@StringRes title: Int, onClick: () -> Unit = { }) {
  Timber.d("MoreItem Composable")
  Box(
    Modifier
      .fillMaxWidth()
      .wrapContentHeight()
      .clickable(onClick = onClick)
  ) {
    Text(
      text = stringResource(id = title),
      style = MaterialTheme.typography.h6,
      modifier = Modifier.padding(16.dp)
    )
  }
  Divider()
}

@Preview(showBackground = true)
@Composable
fun MoreItemPreview() {
  MaterialTheme {
    Box(Modifier.padding(bottom = 2.dp)) {
      Column {
        MoreItem(R.string.about_title)
        MoreItem(R.string.settings_title)
      }
    }
  }
}
