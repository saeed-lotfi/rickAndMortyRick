package com.saeedlotfi.rickandmortyrick.data.local.repository

import com.saeedlotfi.rickandmortyrick.core.Resource
import com.saeedlotfi.rickandmortyrick.data.local.Failure
import retrofit2.Response

abstract class BaseRepository {


    suspend fun <T> callApi(call: suspend () -> Response<T>): Resource<T> {
        return try {
            val response = call()
            Resource.success(response.body()!!)
        } catch (exception: Failure) {
            Resource.error(null, exception)
        }

    }
}