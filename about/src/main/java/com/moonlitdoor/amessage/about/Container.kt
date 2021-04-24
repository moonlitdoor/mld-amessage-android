package com.moonlitdoor.amessage.about

abstract class Container<T>(private val container: List<T> = listOf()) : List<T> by container
