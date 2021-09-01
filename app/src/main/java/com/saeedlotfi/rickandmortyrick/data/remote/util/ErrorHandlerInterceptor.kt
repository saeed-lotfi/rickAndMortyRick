package com.saeedlotfi.rickandmortyrick.data.remote.util

import android.content.Context
import com.google.gson.Gson
import com.saeedlotfi.rickandmortyrick.data.remote.BaseErrorDto
import com.saeedlotfi.rickandmortyrick.data.remote.Failure
import com.saeedlotfi.rickandmortyrick.util.isNetworkAvailable
import okhttp3.Interceptor
import okhttp3.Response
import retrofit2.HttpException
import java.net.UnknownHostException

class ErrorHandlerInterceptor constructor(val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!context.isNetworkAvailable())
            throw Failure.NoConnectivityError

        val response = try {
            chain.proceed(chain.request())

        } catch (ex: Exception) {
            throw when (ex) {
                is UnknownHostException, is IllegalArgumentException -> Failure.UnknownHostError()
                is HttpException -> Failure.HttpError(ex.code(), ex.message())

                else -> Failure.GeneralError(ex.message)
            }
        }
        return when (response.isSuccessful) {
            true -> {
                response.body?.let {
                    response
                } ?: run {
                    throw Failure.EmptyResponse
                }
            }
            false -> throw
            Failure.HttpError(response.code, handleError(response))

        }
    }

    private fun handleError(response: Response): String {
        val errorString = response.body?.string()
        return try {
            Gson().fromJson(errorString, BaseErrorDto::class.java).errorMessage
        } catch (ex: Exception) {
            ""
        }
    }
}
