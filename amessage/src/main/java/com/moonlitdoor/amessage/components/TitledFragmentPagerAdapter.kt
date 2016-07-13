package com.moonlitdoor.amessage.components

class TitledFragmentPagerAdapter(private val context: androidx.fragment.app.FragmentActivity, private val pages: List<TitledFragmentPagerAdapter.TitledFragment>) : androidx.fragment.app.FragmentPagerAdapter(context.supportFragmentManager) {

  override fun getItem(position: Int) = pages[position]

  override fun getCount() = pages.size

  override fun getPageTitle(position: Int): CharSequence? {
    return context.getString(pages[position].getTitleId())
  }

  abstract class TitledFragment : androidx.fragment.app.Fragment() {

    abstract fun getTitleId(): Int

  }

}