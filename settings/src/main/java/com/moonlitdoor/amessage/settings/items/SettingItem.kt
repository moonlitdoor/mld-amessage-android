package com.moonlitdoor.amessage.settings.items

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moonlitdoor.amessage.settings.R
import timber.log.Timber

@Composable
fun SettingItem(@StringRes title: Int, modifier: Modifier = Modifier, @StringRes description: Int? = null, onClick: (() -> Unit)? = null) {
  SettingItem(stringResource(id = title), modifier, description?.let { stringResource(id = it) }, onClick)
}

@Composable
fun SettingItem(title: String, modifier: Modifier = Modifier, description: String? = null, onClick: (() -> Unit)? = null) {
  Timber.d("SettingItem Composable")
  Column(
    modifier
      .clickable(onClick = onClick ?: {}, enabled = onClick != null)
      .padding(16.dp)
  ) {
    Text(
      text = title,
      style = MaterialTheme.typography.h6,
    )
    description?.let {
      Text(
        text = it,
        style = MaterialTheme.typography.overline,
      )
    }
  }
}

@Preview(showBackground = true)
@Composable
fun SettingItemPreview() {
  MaterialTheme {
    Box(Modifier.padding(bottom = 2.dp)) {
      Column {
        SettingItem(R.string.about_title, description = R.string.about_title)
        Divider()
        SettingItem(R.string.settings_title)
        Divider()
      }
    }
  }
}
