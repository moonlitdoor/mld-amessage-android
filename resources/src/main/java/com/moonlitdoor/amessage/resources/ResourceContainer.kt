package com.moonlitdoor.amessage.resources

import androidx.annotation.StringRes
import com.moonlitdoor.amessage.root.Root

object ResourceContainer {

  fun getString(@StringRes id: Int): String = Root.get().getString(id)

//  fun getString(@StringRes id: Int, vararg formatArgs: Any): String = res.getString(id, formatArgs)

}