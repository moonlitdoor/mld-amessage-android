package com.moonlitdoor.amessage.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations

fun <T> LiveData<T>.observe(owner: LifecycleOwner, block: (T) -> Unit) = this.observe(owner, Observer { block.invoke(it) })

inline fun <T> LiveData<T>.observeNotNull(owner: LifecycleOwner, crossinline block: (T) -> Unit) {
  this.observe(owner, Observer {
    it ?: return@Observer
    block(it)
  })
}

fun <X, Y> LiveData<X>.map(block: (X) -> Y): LiveData<Y> = Transformations.map(this, block)

fun <X, Y> LiveData<X>.switchMap(block: (X) -> LiveData<Y>): LiveData<Y> = Transformations.switchMap(this, block)

fun <X> LiveData<X>.and(block: (X) -> Unit): LiveData<X> {
  return Transformations.map(this) {
    block.invoke(it)
    it
  }
}