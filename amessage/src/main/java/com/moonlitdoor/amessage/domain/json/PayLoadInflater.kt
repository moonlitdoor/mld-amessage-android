package com.moonlitdoor.amessage.domain.json

interface PayLoadInflater<T> {

  fun inflate(json: String): T

}