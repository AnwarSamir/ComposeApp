package com.example.base

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

/**
 * @author Anwar ElSayed
 * Base view model that will take the base Failure,empty status,Loader types  or pagination to handle it with the view
 * */
open class BaseViewModel : ViewModel() {
    protected val _failureStateFlow = MutableSharedFlow<Resource.Failure>()
    val failureStateFlow = _failureStateFlow.asSharedFlow()
    val loadingState: MutableState<Boolean> = mutableStateOf(false)
    var swipeToRefStateFLow: MutableStateFlow<Boolean> = MutableStateFlow(false)
    var pagingLoadingStateFLow: MutableStateFlow<Boolean> = MutableStateFlow(false)

    val exceptionHandler = CoroutineExceptionHandler { context, exception ->
        viewModelScope.launch {
            _failureStateFlow.emit(
                Resource.Failure(
                    false,
                    ErrorCodes.EXCEPTION,
                    exception.localizedMessage
                )
            )
        }
    }

    fun setProgress(
        progressType: ProgressTypes,
        isLoading: Boolean = false
    ) {
        viewModelScope.launch {
            when (progressType) {
                ProgressTypes.FULL_PROGRESS -> loadingState.value = isLoading
                ProgressTypes.PAGING_PROGRESS -> pagingLoadingStateFLow.emit(isLoading)
                ProgressTypes.SWIPE_TO_REFRESH_PROGRESS -> swipeToRefStateFLow.emit(isLoading)
                else -> Unit
            }
        }
    }


    /**
     * Launches a coroutine that processes an API response using the provided request,
     * progress type, success and failure callbacks.
     *
     * @param request A lambda function that returns a Flow of Resource objects.
     * @param progressType An optional ProgressTypes parameter that specifies the type of progress to show during the operation.
     * @param onSuccess A lambda function that takes the result of a successful API call and performs some action on it.
     * @param onFailure A lambda function that takes a Resource.Failure object and performs some action on it.
     *
     * @return A Job object that represents the coroutine.
     */
    inline fun <T> processApiResponse(
        crossinline request: () -> Flow<Resource<T>>,
        progressType: ProgressTypes = ProgressTypes.NONE,
        crossinline onSuccess: (T?) -> Unit,
        crossinline onFailure: (Resource.Failure) -> Unit
    ) = launch {
        request().onStart {
            setProgress(isLoading = true, progressType = progressType)
        }.onCompletion {
            setProgress(progressType = progressType)
        }.collect {
            when (it) {
                is Resource.Success -> onSuccess(it.value)
                is Resource.Failure -> onFailure(it)
            }
        }
    }

    fun ViewModel.launch(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch {
            block()
        }
    }
}

