package com.saeedlotfi.rickandmortyrick.data.local.repository

import com.saeedlotfi.rickandmortyrick.core.Resource
import com.saeedlotfi.rickandmortyrick.data.remote.ApiService
import com.saeedlotfi.rickandmortyrick.data.remote.model.CharactersResponseModel
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(private val api: ApiService) :
    CharacterRepository, BaseRepository() {

    override suspend fun getCharacters(): Resource<CharactersResponseModel> {
        return callApi {
            api.getCharacters()
        }
    }

}