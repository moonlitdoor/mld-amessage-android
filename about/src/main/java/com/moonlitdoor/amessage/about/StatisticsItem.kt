package com.moonlitdoor.amessage.about

import android.annotation.SuppressLint
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
import com.moonlitdoor.amessage.extensions.Ensure
import com.moonlitdoor.amessage.theme.Colors
import timber.log.Timber
import java.net.URLEncoder

enum class StatisticsItemType {
  VALUE,
  URL,
  EMAIL,
  TWITTER
}

@Composable
fun StatisticsItem(@StringRes title: Int, @StringRes value: Int, type: StatisticsItemType = StatisticsItemType.VALUE) {
  StatisticsItem(title = title, value = stringResource(id = value), type = type)
}

@Composable
fun StatisticsItem(@StringRes title: Int, value: String, type: StatisticsItemType = StatisticsItemType.VALUE) {
  StatisticsItem(title = stringResource(id = title), value = value, type = type)
}

@SuppressLint("QueryPermissionsNeeded")
@Composable
fun StatisticsItem(title: String, value: String? = null, type: StatisticsItemType = StatisticsItemType.VALUE) {
  Timber.d("StatisticsItem")

  val context = LocalContext.current
  Column(
    modifier = Modifier
      .fillMaxWidth()
      .clickable(enabled = type != StatisticsItemType.VALUE) {
        Ensure exhaustive when (type) {
          StatisticsItemType.URL -> context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(value)))
          StatisticsItemType.EMAIL -> context.startActivity(
            Intent(Intent.ACTION_SENDTO).also {
              it.data = Uri.parse("mailto:")
              it.putExtra(Intent.EXTRA_EMAIL, value)
            }
          )
          StatisticsItemType.TWITTER -> context.startActivity(
            Intent(
              Intent.ACTION_VIEW,
              Uri.parse("https://twitter.com/intent/tweet?text=${URLEncoder.encode(value, "UTF-8")}")
            )
          )
          StatisticsItemType.VALUE -> {
          }
        }
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
        style = MaterialTheme.typography.body1.run {
          if (type == StatisticsItemType.VALUE) this else copy(color = Colors.Link)
        },
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
    StatisticsItem(title = "Privacy Policy", value = "https://www.google.com", type = StatisticsItemType.URL)
    Divider()
  }
}
