package com.saeedlotfi.rickandmortyrick.data.remote

import com.saeedlotfi.rickandmortyrick.data.remote.model.CharactersResponseModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("character")
    suspend fun getCharacters(): Response<CharactersResponseModel>
}