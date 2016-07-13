package com.moonlitdoor.amessage.components

import android.content.Context
import android.graphics.Canvas
import androidx.core.content.ContextCompat
import com.moonlitdoor.amessage.R

class ItemDecorationDivider(context: Context) : androidx.recyclerview.widget.RecyclerView.ItemDecoration() {

  private val divider = ContextCompat.getDrawable(context, R.drawable.item_decoration_divider)

  override fun onDrawOver(c: Canvas, parent: androidx.recyclerview.widget.RecyclerView, state: androidx.recyclerview.widget.RecyclerView.State) {
    divider?.let {
      val left = parent.paddingLeft
      val right = parent.width - parent.paddingRight

      val childCount = parent.childCount
      for (i in 0 until childCount) {
        val child = parent.getChildAt(i)
        val params = child.layoutParams as androidx.recyclerview.widget.RecyclerView.LayoutParams
        val top = child.bottom + params.bottomMargin
        val bottom = top + divider.intrinsicHeight

        divider.setBounds(left, top, right, bottom)
        divider.draw(c)
      }
    }
  }

}