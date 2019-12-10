package com.moonlitdoor.amessage.about

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.moonlitdoor.amessage.about.databinding.BottomSheetDialogAcknowledgementBinding

internal class AcknowledgementsBottomSheetDialog(context: Context, acknowledgement: Acknowledgement) : com.google.android.material.bottomsheet.BottomSheetDialog(context), View.OnScrollChangeListener {

  private val binding: BottomSheetDialogAcknowledgementBinding = BottomSheetDialogAcknowledgementBinding.inflate(LayoutInflater.from(context), null, false)

  init {
    this.binding.title = acknowledgement.title
    this.binding.scrollView.setOnScrollChangeListener(this)
    this.setContentView(this.binding.root)
    try {
      getContext().resources.openRawResource(R.raw.license_apache_2_0).use { inputStream ->
        val bytes = ByteArray(inputStream.available())
        inputStream.read(bytes)
        this.binding.textView.text = String(bytes)
      }
    } catch (e: Exception) {
      this.binding.textView.text = context.getString(R.string.error_no_help)
    }

//    val bottomSheetBehavior = object : com.google.android.material.bottomsheet.BottomSheetBehavior<View>() {
//      override fun onInterceptTouchEvent(parent: androidx.coordinatorlayout.widget.CoordinatorLayout, child: View, event: MotionEvent): Boolean {
//        return super.onInterceptTouchEvent(parent, child, event)
//      }
//    }

//    bottomSheetBehavior.setBottomSheetCallback(object : com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback() {
//      override fun onStateChanged(bottomSheet: View, newState: Int) {
//        when (newState) {
//          com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_HIDDEN -> {
//            dismiss()
//            return
//          }
//        }
//      }

//      override fun onSlide(bottomSheet: View, slideOffset: Float) {}
//    })
  }

  override fun onScrollChange(view: View, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int) {
    if (scrollY <= 96) {
      this.binding.toolbar.elevation = (scrollY / 4).toFloat()
      //  TODO
      //      ObjectAnimator animation = ObjectAnimator.ofFloat(this.binding.toolbar, "elevation", scrollY / 4);
      //      animation.setDuration(1000);
      //      animation.start();
      //      animation.
    }
  }

  companion object {
    @JvmStatic
    fun show(context: Context, acknowledgement: Acknowledgement) = AcknowledgementsBottomSheetDialog(context, acknowledgement).show()
  }

}
