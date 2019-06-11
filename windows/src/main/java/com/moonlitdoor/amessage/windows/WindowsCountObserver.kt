package com.moonlitdoor.amessage.windows

import android.view.MenuItem
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

class WindowsCountObserver(owner: LifecycleOwner, liveData: LiveData<Int>, private val menuItem: MenuItem) : Observer<Int> {

  init {
    liveData.observe(owner, this)
  }

  override fun onChanged(count: Int?) {
    count?.let {
      menuItem.setIcon(
        when (it) {
          0 -> R.drawable.ic_screens_none_black_24dp
          1 -> R.drawable.ic_screens_1_black_24dp
          2 -> R.drawable.ic_screens_2_black_24dp
          3 -> R.drawable.ic_screens_3_black_24dp
          4 -> R.drawable.ic_screens_4_black_24dp
          5 -> R.drawable.ic_screens_5_black_24dp
          6 -> R.drawable.ic_screens_6_black_24dp
          7 -> R.drawable.ic_screens_7_black_24dp
          8 -> R.drawable.ic_screens_8_black_24dp
          9 -> R.drawable.ic_screens_9_black_24dp
          else -> R.drawable.ic_screens_more_black_24dp
        }
      )
    }
  }
}