package com.app.bank_misr.data.di

import android.content.Context
import com.app.bank_misr.BuildConfig
import com.app.bank_misr.data.app.App
import com.app.bank_misr.data.local_storage.prefs.PrefStore
import com.app.bank_misr.data.network.ApiService
import com.app.bank_misr.data.util.cast
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Ahmed Elmokadim
 * elmokadim@gmail.com
 * 15/07/2023
 */
@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

  @Singleton
  @Provides
  fun provideLoggingInterceptor(
    @ApplicationContext context: Context
  ) = HttpLoggingInterceptor().apply { if (context.cast<App>().debug) level = BODY }

  @Singleton
  @Provides
  fun provideOkHttpClient(
    loggingInterceptor: HttpLoggingInterceptor
  ) = OkHttpClient.Builder()
    .connectTimeout(1, TimeUnit.MINUTES)
    .readTimeout(1, TimeUnit.MINUTES)
    .addInterceptor(Interceptor {
      val request = it.request().newBuilder()
        .header("Content-Type", "application/json")
        .header("Platform", "android")
        .build()
      it.proceed(request)
    })
    .addInterceptor(loggingInterceptor)
    .build()

  @Singleton
  @Provides
  fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BuildConfig.BASE_URL)
    .client(okHttpClient)
    .build()

  @Singleton
  @Provides
  fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)
}