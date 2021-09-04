package com.saeedlotfi.rickandmortyrick.data.remote

data class Resource<out T>(val status: Status,
                           val data: T?,
                           val error: Failure? = null) {
    companion object {
        fun <T> success(data: T): Resource<T> = Resource(status = Status.SUCCESS, data = data, error = null)

        fun <T> error(data: T?, error: Failure? = null): Resource<T> =
            Resource(status = Status.ERROR, data = data, error = error)
    }
}