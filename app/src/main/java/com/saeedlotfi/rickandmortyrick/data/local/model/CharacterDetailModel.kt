package com.saeedlotfi.rickandmortyrick.data.local.model

import java.io.Serializable

data class CharacterDetailModel(
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val episode: List<String>,
    val imgProfile: String
) : Serializable