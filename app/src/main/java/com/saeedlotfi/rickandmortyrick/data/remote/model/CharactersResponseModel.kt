package com.saeedlotfi.rickandmortyrick.data.remote.model

import com.google.gson.annotations.SerializedName

data class CharactersResponseModel(
   @SerializedName("results") val characterResponseModels: List<CharacterResponseModel>
)