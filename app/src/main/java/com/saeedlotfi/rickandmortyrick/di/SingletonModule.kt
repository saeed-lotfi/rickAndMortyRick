package com.saeedlotfi.rickandmortyrick.di

import android.content.Context
import com.saeedlotfi.rickandmortyrick.data.remote.ApiService
import com.saeedlotfi.rickandmortyrick.data.remote.util.ErrorHandlerInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

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
            .baseUrl("https://rickandmortyapi.com/api")
            .build()
            .create(ApiService::class.java)


    @Provides
    @Singleton
    fun createOkHttpClient(errorOkHttpClient: Interceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(errorOkHttpClient)
            .build()


    @Singleton
    @Provides
    fun provideErrorHandlerInterceptor(@ApplicationContext context: Context) =
        ErrorHandlerInterceptor(context)

}
