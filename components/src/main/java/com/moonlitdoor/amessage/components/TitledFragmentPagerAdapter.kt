package com.moonlitdoor.amessage.components

import androidx.fragment.app.Fragment


class TitledFragmentPagerAdapter(private val context: androidx.fragment.app.FragmentActivity, private val pages: List<TitledFragment>) : androidx.fragment.app.FragmentPagerAdapter(context.supportFragmentManager) {

  override fun getItem(position: Int) = pages[position]

  override fun getCount() = pages.size

  override fun getPageTitle(position: Int): CharSequence? {
    return context.getString(pages[position].getTitleId())
  }

  abstract class TitledFragment : Fragment() {

    abstract fun getTitleId(): Int

  }

}