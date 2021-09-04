package com.saeedlotfi.rickandmortyrick.data.local.repository

import com.saeedlotfi.rickandmortyrick.core.Resource
import com.saeedlotfi.rickandmortyrick.data.remote.model.CharactersResponseModel

interface CharacterRepository {

    /**
     * get characters
     */
    suspend fun getCharacters(): Resource<CharactersResponseModel>


}