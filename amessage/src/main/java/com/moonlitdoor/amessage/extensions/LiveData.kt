package com.moonlitdoor.amessage.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations

inline fun <T> LiveData<T>.observe(owner: LifecycleOwner, crossinline block: (T) -> Unit) = this.observe(owner, Observer { block.invoke(it) })

inline fun <T> LiveData<T>.observeNotNull(owner: LifecycleOwner, crossinline block: (T) -> Unit) {
  this.observe(owner, Observer {
    it ?: return@Observer
    block(it)
  })
}

fun <X, Y> LiveData<X>.map(body: (X) -> Y): LiveData<Y> {
  return Transformations.map(this, body)
}

fun <X, Y> LiveData<X>.switchMap(body: (X) -> LiveData<Y>): LiveData<Y> {
  return Transformations.switchMap(this, body)
}