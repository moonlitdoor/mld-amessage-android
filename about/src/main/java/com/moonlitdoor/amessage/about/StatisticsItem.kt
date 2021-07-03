package com.moonlitdoor.amessage.about

import android.content.Intent
import android.net.Uri
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moonlitdoor.amessage.theme.Colors

@Composable
fun StatisticsItem(@StringRes title: Int, value: String? = null, @StringRes url: Int? = null) {
  StatisticsItem(title = stringResource(id = title), value = value, url = url?.let { stringResource(id = it) })
}

@Composable
fun StatisticsItem(title: String, value: String? = null, url: String? = null) {
  val context = LocalContext.current
  Column(
    modifier = Modifier
      .fillMaxWidth()
      .clickable(enabled = url != null) {
        context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
      }
      .padding(16.dp)
  ) {
    Text(
      text = title,
      style = MaterialTheme.typography.h6,
    )
    value?.let {
      Text(
        text = it,
        style = MaterialTheme.typography.body1,
      )
    }
    url?.let {
      Text(
        text = it,
        style = MaterialTheme.typography.body1.copy(color = Colors.Link),
      )
    }

  }
}

@Preview(showBackground = true)
@Composable
fun StatisticsItemPreview() {
  Column(modifier = Modifier.padding(2.dp)) {
    StatisticsItem(title = "Version")
    Divider()
    StatisticsItem(title = "Version2", value = "0.3.5")
    Divider()
    StatisticsItem(title = "Privacy Policy", url = "https://www.google.com")
    Divider()
  }

}
