@file:Suppress("RemoveExplicitTypeArguments")

package com.moonlitdoor.amessage.network

import com.moonlitdoor.amessage.network.client.FirebaseClient
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.net.URL

val networkDi = listOf(module {

  single {
    HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
      override fun log(message: String) {
        Timber.i(message)
      }
    }).also {
      it.level = HttpLoggingInterceptor.Level.BODY //if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
    }
  }
  single {
    OkHttpClient.Builder()
      .addNetworkInterceptor(get<HttpLoggingInterceptor>())
      .build()
  }
  single { URL(getProperty<String>("firebase_url")) }
  single {
    Retrofit.Builder()
      .baseUrl(get<URL>())
      .client(get<OkHttpClient>())
      .addConverterFactory(GsonConverterFactory.create())
      .build()
  }
  single { get<Retrofit>().create(FirebaseClient::class.java) }
  single { NetworkClient(get<FirebaseClient>()) }

})
