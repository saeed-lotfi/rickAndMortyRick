package com.saeedlotfi.rickandmortyrick.di

import com.saeedlotfi.rickandmortyrick.data.local.repository.CharacterRepository
import com.saeedlotfi.rickandmortyrick.data.local.repository.CharacterRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryBinds {

    @Binds
    @ViewModelScoped
    abstract fun bindCharacterRepository(characterRepositoryImpl: CharacterRepositoryImpl): CharacterRepository


}