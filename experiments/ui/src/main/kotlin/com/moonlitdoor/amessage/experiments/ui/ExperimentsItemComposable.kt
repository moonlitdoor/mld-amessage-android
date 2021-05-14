package com.moonlitdoor.amessage.experiments.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Divider
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.isFocused
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.moonlitdoor.amessage.experiments.Experiment
import com.moonlitdoor.amessage.experiments.FirebaseRemoteConfigWrapper
import com.moonlitdoor.amessage.root.Root
import timber.log.Timber

@Composable
fun ExperimentsItem(item: Experiment<*>) {
  Timber.d("ExperimentsItem Composable")
  ConstraintLayout(
    modifier = Modifier
      .fillMaxWidth()
      .wrapContentHeight()
      .padding(8.dp)
  ) {
    val (title, statistics, dropdown) = createRefs()
    val guideline = createGuidelineFromAbsoluteRight(fraction = .4F)
    Text(
      modifier = Modifier
        .constrainAs(title) {
          top.linkTo(parent.top)
          start.linkTo(parent.start)
        },
      text = item.title ?: item.key,
    )
    Column(modifier = Modifier
      .wrapContentSize()
      .constrainAs(statistics) {
        linkTo(
          top = title.bottom,
          bottom = parent.bottom,
          verticalBias = 0F,
          start = title.start,
          startMargin = 4.dp,
          end = guideline,
          horizontalBias = 0F
        )

      }) {
      item.description?.let {
        Text(text = it, style = MaterialTheme.typography.overline, maxLines = 2)
      }
      Text(text = "Key: ${item.key}", style = MaterialTheme.typography.overline)
      Text(text = "Remote: ${item.remoteValue}", style = MaterialTheme.typography.overline)
    }
    Column(
      modifier = Modifier.constrainAs(dropdown) {
        centerVerticallyTo(parent)
        linkTo(start = guideline, end = parent.end)
      },
    ) {
      val focusManager = LocalFocusManager.current
      var expanded by remember { mutableStateOf(false) }
      OutlinedTextField(
        modifier = Modifier
          .wrapContentHeight()
          .fillMaxWidth()
          .onFocusChanged {
            expanded = it.isFocused
          },
        value = item.localValue,
        readOnly = true,
        onValueChange = {},
        label = { Text("Local Value") }
      )
      DropdownMenu(expanded = expanded, onDismissRequest = {
        focusManager.clearFocus()
      }) {
        item.options.forEach {
          DropdownMenuItem(onClick = {
            focusManager.clearFocus()
            item.localValue = it
          }) {
            Text(text = it)
          }
        }
      }
    }
  }
  Divider()
}

@Preview(showBackground = true)
@Composable
fun ExperimentsItemPreview() {
  val previewKey = "exp_test_data"
  Root.init(LocalContext.current)
  FirebaseRemoteConfigWrapper.get(object : FirebaseRemoteConfigWrapper {
    override fun setDefaults(defaults: Map<String, Any>) {}

    override fun getString(key: String): String = if (key == previewKey) {
      Experiment.BOOLEAN.TRUE.toString()
    } else {
      Experiment.BOOLEAN.FALSE.toString()
    }


  })
  Column(Modifier.padding(2.dp)) {
    ExperimentsItem(Experiment(key = previewKey, title = "Title"))
    ExperimentsItem(Experiment(key = "other_key", defaultValue = Experiment.BOOLEAN.TRUE))
    ExperimentsItem(Experiment(key = "other_key", title = "Title", description = "This is a much longer description, and now we are going to make it even longer.®®"))
  }
}
