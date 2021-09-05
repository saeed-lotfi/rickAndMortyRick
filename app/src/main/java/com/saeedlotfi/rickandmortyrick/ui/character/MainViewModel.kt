package com.saeedlotfi.rickandmortyrick.ui.character

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.saeedlotfi.rickandmortyrick.base.BaseViewModel
import com.saeedlotfi.rickandmortyrick.core.Status
import com.saeedlotfi.rickandmortyrick.data.repository.CharacterRepository
import com.saeedlotfi.rickandmortyrick.data.remote.model.CharactersResponseModel
import com.saeedlotfi.rickandmortyrick.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val characterRepository: CharacterRepository) :
    BaseViewModel() {

    private val _characters = SingleLiveEvent<CharactersResponseModel>()
    val characters: LiveData<CharactersResponseModel> get() = _characters


    init {
        getAllCharacter()
    }

    private fun getAllCharacter() {
        viewModelScope.launch(Dispatchers.IO + genericExceptionHandler) {

            enableLoading()

            val response = characterRepository.getCharacters()

            when (response.status) {
                Status.SUCCESS -> {
                    _characters.postValue(response.data!!)
                }
                Status.ERROR -> {
                    manageException(response)
                }
            }

            disableLoading()


        }
    }

}