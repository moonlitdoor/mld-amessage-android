package com.moonlitdoor.amessage.network

import com.moonlitdoor.amessage.network.client.FirebaseClient
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import javax.inject.Named

interface NetworkDI {


  @Module
  class NetworkModule {

    @Provides
    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
      override fun log(message: String) {
        Timber.i(message)
      }
    }).also {
      it.level = HttpLoggingInterceptor.Level.BODY //if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
    }

    @Provides
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor) =
      OkHttpClient.Builder()
        .addNetworkInterceptor(httpLoggingInterceptor)
        .build()

    @Provides
    fun providesRetrofit(@Named("base_url") baseUrl: String, okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
      .baseUrl(baseUrl)
      .client(okHttpClient)
      .addConverterFactory(GsonConverterFactory.create())
      .build()

    @Provides
    fun providesFirebaseClient(retrofit: Retrofit): FirebaseClient = retrofit.create(FirebaseClient::class.java)

    @Provides
    fun providesNetworkClient(firebaseClient: FirebaseClient): NetworkClient = NetworkClient(firebaseClient)

  }

  @Module
  open class BaseUrlModule {

    @Provides
    @Named("base_url")
    open fun providesFirebaseUrl() = BuildConfig.BASE_URL

  }
}
