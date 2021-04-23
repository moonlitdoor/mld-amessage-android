package com.moonlitdoor.amessage.settings

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

@Composable
fun SettingItem(@StringRes title: Int, @StringRes description: Int? = null, onClick: (() -> Unit)? = null) {
  Column(
    Modifier
      .fillMaxWidth()
      .wrapContentHeight()
      .clickable(onClick = onClick ?: {}, enabled = onClick != null)
      .padding(16.dp)
  ) {
    Text(
      text = stringResource(id = title),
      style = MaterialTheme.typography.h6,
    )
    description?.let {
      Text(
        text = stringResource(id = it),
        style = MaterialTheme.typography.overline,
      )
    }

  }
  Divider()
}

@Preview(showBackground = true)
@Composable
fun SettingItemPreview() {
  MaterialTheme {
    Box(Modifier.padding(bottom = 2.dp)) {
      Column {
        SettingItem(R.string.about_title, R.string.about_title)
        SettingItem(R.string.settings_title)
      }
    }
  }
}