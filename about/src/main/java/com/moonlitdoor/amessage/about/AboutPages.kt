package com.moonlitdoor.amessage.about

import androidx.annotation.StringRes

enum class AboutPages(@StringRes val title: Int) {
  STATISTICS(R.string.about_statistics),
  ACKNOWLEDGEMENTS(R.string.about_acknowledgements)
}
