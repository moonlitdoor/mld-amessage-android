@file:Suppress("RemoveExplicitTypeArguments")

package com.moonlitdoor.amessage.network

import android.content.Context
import android.preference.PreferenceManager
import com.moonlitdoor.amessage.network.client.FirebaseClient
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber

val networkDi = module {

  single { PreferenceManager.getDefaultSharedPreferences(androidContext()) }
  single { get<Context>().resources }
  single { _ -> HttpLoggingInterceptor { Timber.i(it) }.setLevel(if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE) }
  single { OkHttpClient.Builder().addNetworkInterceptor(get<HttpLoggingInterceptor>()).build() }
  single {
    Retrofit.Builder()
      .baseUrl("https://fcm.googleapis.com")
      .client(get<OkHttpClient>())
      .addConverterFactory(GsonConverterFactory.create())
      .build()
  }
  single { get<Retrofit>().create(FirebaseClient::class.java) }

}
