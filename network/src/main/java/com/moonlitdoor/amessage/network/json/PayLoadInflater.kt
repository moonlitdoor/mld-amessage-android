package com.moonlitdoor.amessage.network.json

interface PayLoadInflater<T> {

  fun inflate(json: String): T

}