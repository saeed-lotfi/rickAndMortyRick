package com.saeedlotfi.rickandmortyrick.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.saeedlotfi.rickandmortyrick.core.Resource
import com.saeedlotfi.rickandmortyrick.core.Status
import com.saeedlotfi.rickandmortyrick.data.local.Failure
import com.saeedlotfi.rickandmortyrick.util.SingleLiveEvent
import kotlinx.coroutines.CoroutineExceptionHandler

abstract class BaseViewModel : ViewModel() {

    private var _handleFailure = SingleLiveEvent<Failure>()
    val handleFailure: SingleLiveEvent<Failure> get() = _handleFailure

    val genericExceptionHandler = CoroutineExceptionHandler { _, _ ->
        disableLoading()
    }

    // is loading showing
    var isLoading = MutableLiveData<Boolean>()

    fun manageException(resource: Resource<Any>) {
        if (resource.status == Status.ERROR) {
            resource.error?.let { error ->
                _handleFailure.postValue(error)
            }
        }
    }

    // if any error accord in coroutines disable the loading
    fun disableLoading() {
        if (isLoading.value == true)
            isLoading.postValue(false)
    }

    fun enableLoading() {
        isLoading.postValue(true)
    }

}