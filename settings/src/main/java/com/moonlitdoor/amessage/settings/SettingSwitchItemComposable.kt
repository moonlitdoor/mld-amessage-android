package com.moonlitdoor.amessage.settings

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Switch
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import timber.log.Timber

@Composable
fun SettingSwitchItem(@StringRes title: Int, checked: Boolean, @StringRes description: Int? = null, onClick: (Boolean) -> Unit) {
  Timber.d("SettingSwitchItem Composable")
  Column(
    Modifier
      .fillMaxWidth()
      .wrapContentHeight()
      .clickable(onClick = { onClick(!checked) })
  ) {
    Row(
      modifier = Modifier
    ) {
      SettingItem(title = title, description = description, enableDivider = false)
      Switch(
        checked = checked, onCheckedChange = null,
        Modifier
          .align(Alignment.CenterVertically)
          .padding(top = 16.dp, end = 16.dp, bottom = 16.dp)
      )
    }
    Divider()
  }
}

@Preview(showBackground = true)
@Composable
fun SettingSwitchItemPreview() {
  MaterialTheme {
    Box(Modifier.padding(bottom = 2.dp)) {
      Column {
        SettingSwitchItem(R.string.about_title, true, R.string.about_title) {}
        SettingSwitchItem(R.string.settings_title, false) {}
      }
    }
  }
}
