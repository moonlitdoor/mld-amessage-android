package com.moonlitdoor.amessage.network

import com.moonlitdoor.amessage.network.client.FirebaseClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

  @Provides
  fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor { message -> Timber.i(message) }.also {
    it.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
  }

  @Provides
  fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor) =
    OkHttpClient.Builder()
      .addNetworkInterceptor(httpLoggingInterceptor)
      .build()

  @Provides
  fun providesRetrofit(@BaseUrl baseUrl: String, okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
    .baseUrl(baseUrl)
    .client(okHttpClient)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

  @Provides
  fun providesFirebaseClient(retrofit: Retrofit): FirebaseClient = retrofit.create(FirebaseClient::class.java)

  @Provides
  fun providesNetworkClient(firebaseClient: FirebaseClient): NetworkClient = NetworkClient(firebaseClient)

  @Provides
  @BaseUrl
  fun providesFirebaseUrl() = BuildConfig.BASE_URL
}
