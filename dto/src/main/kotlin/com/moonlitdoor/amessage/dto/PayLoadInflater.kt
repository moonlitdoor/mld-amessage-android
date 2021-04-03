package com.moonlitdoor.amessage.dto

interface PayLoadInflater<T> {

  fun inflate(json: String): T

}