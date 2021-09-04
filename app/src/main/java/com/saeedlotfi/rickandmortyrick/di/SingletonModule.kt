package com.saeedlotfi.rickandmortyrick.di

import android.content.Context
import com.saeedlotfi.rickandmortyrick.data.remote.ApiService
import com.saeedlotfi.rickandmortyrick.data.remote.util.ErrorHandlerInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

private const val TIMEOUT:Long = 1 // time out

@Module
@InstallIn(SingletonComponent::class)
object SingletonModule {

    @Singleton
    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory =
        GsonConverterFactory.create()

    @Provides
    @Singleton

    fun provideRetrofit(okHttp: OkHttpClient, gsonConverterFactory: GsonConverterFactory) =
        Retrofit.Builder()
            .client(okHttp)
            .addConverterFactory(gsonConverterFactory)
            .baseUrl("https://rickandmortyapi.com/api/")
            .build()
            .create(ApiService::class.java)


    @Provides
    @Singleton
    fun createOkHttpClient(errorOkHttpClient: ErrorHandlerInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(errorOkHttpClient)
            .connectTimeout(TIMEOUT, TimeUnit.MINUTES)
            .writeTimeout(TIMEOUT, TimeUnit.MINUTES)
            .readTimeout(TIMEOUT, TimeUnit.MINUTES)
            .build()


    @Singleton
    @Provides
    fun provideErrorHandlerInterceptor(@ApplicationContext context: Context): ErrorHandlerInterceptor =
        ErrorHandlerInterceptor(context)

}
