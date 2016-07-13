package com.moonlitdoor.amessage.domain.model

abstract class Container<T>(private val container: List<T> = listOf()) : List<T> by container
