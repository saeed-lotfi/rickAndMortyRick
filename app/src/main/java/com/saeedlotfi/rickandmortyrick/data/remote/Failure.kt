package com.saeedlotfi.rickandmortyrick.data.remote

import com.google.gson.annotations.SerializedName
import java.io.IOException
import java.lang.Exception

sealed class Failure : Exception() {
    class UnknownHostError : Failure()
    class HttpError(var code: Int, override var message: String) : Failure()
    object NoConnectivityError : Failure()
    object EmptyResponse : Failure()
    class GeneralError(override var message: String?) :
        Failure() // this error use for manage other error not planning in application
}

data class BaseErrorDto(
    @SerializedName("error") var errorMessage: String = ""
)
