package com.moonlitdoor.amessage.components

import android.content.Context
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import com.moonlitdoor.amessage.components.databinding.BottomSheetDialogWhatsNewBinding
import com.moonlitdoor.amessage.components.databinding.ListItemWhatsNewBinding
import java.util.*

class WhatsNewBottomSheetDialog(context: Context) : com.google.android.material.bottomsheet.BottomSheetDialog(context) {

  private val binding: BottomSheetDialogWhatsNewBinding = BottomSheetDialogWhatsNewBinding.inflate(LayoutInflater.from(context), null, false)

  init {
    this.setContentView(this.binding.root)
    val data: MutableList<String>
    val stringArray = getContext().resources.getStringArray(R.array.metadata_whats_new)
    if (stringArray.isNotEmpty()) {
      data = mutableListOf(*stringArray)
    } else {
      data = ArrayList()
      data.add(getContext().resources.getString(R.string.metadata_whats_new_bug_fixes))
    }
    this.binding.recyclerView.adapter = WhatsNewAdapter(data)

//    val bottomSheetBehavior = object : com.google.android.material.bottomsheet.BottomSheetBehavior<View>() {
//      override fun onInterceptTouchEvent(parent: androidx.coordinatorlayout.widget.CoordinatorLayout, child: View, event: MotionEvent): Boolean {
//        return super.onInterceptTouchEvent(parent, child, event)
//      }
//    }
//
//    bottomSheetBehavior.setBottomSheetCallback(object : com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback() {
//      override fun onStateChanged(bottomSheet: View, newState: Int) {
////        when (newState) {
////          com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_HIDDEN -> {
////            dismiss()
////            return
////          }
////        }
//      }

//      override fun onSlide(bottomSheet: View, slideOffset: Float) {}
//    })

  }

  override fun dismiss() {
    super.dismiss()
//    PreferenceManager.getDefaultSharedPreferences(context).edit().putInt(Constants.SharedPreferences.VERSION_CODE, BuildConfig.VERSION_CODE).apply()
  }

  private class WhatsNewAdapter(private val data: List<String>) : androidx.recyclerview.widget.RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
      return ViewHolder(ListItemWhatsNewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
      holder.bind(this.data[position])
    }

    override fun getItemCount(): Int {
      return this.data.size
    }
  }

  private class ViewHolder internal constructor(private val binding: ListItemWhatsNewBinding) : androidx.recyclerview.widget.RecyclerView.ViewHolder(binding.root) {

    internal fun bind(data: String) {
      this.binding.data = data
    }

  }

  companion object {

    fun show(context: Context?) {
//      if (PreferenceManager.getDefaultSharedPreferences(context).getInt(Constants.SharedPreferences.VERSION_CODE, 0) < BuildConfig.VERSION_CODE) {
//        forceShow(context)
//      }
    }

    fun setMenuItemListener(context: Context?, drawerLayout: androidx.drawerlayout.widget.DrawerLayout, menuItem: MenuItem?) {
      menuItem?.setOnMenuItemClickListener { _ ->
        drawerLayout.closeDrawer(GravityCompat.START)
        forceShow(context)
        true
      }
    }

    private fun forceShow(context: Context?) {
      context?.let {
        WhatsNewBottomSheetDialog(context).show()
      }
    }
  }

}
