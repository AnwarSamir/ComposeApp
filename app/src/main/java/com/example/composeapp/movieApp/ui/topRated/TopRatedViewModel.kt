package com.example.composeapp.movieApp.ui.topRated

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.base.BaseViewModel
import com.example.base.ProgressTypes
import com.example.composeapp.movieApp.models.entities.Person
import com.example.composeapp.movieApp.models.topRated.TopRatedResponse
import com.example.composeapp.movieApp.repository.TopRatedRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopRatedViewModel @Inject constructor(private val topRatedRepo: TopRatedRepository) :
    BaseViewModel() {

    val topRated: MutableState<TopRatedResponse> = mutableStateOf(TopRatedResponse())

    init {
        fetchTopRated()
    }

    private fun fetchTopRated() = viewModelScope.launch {
        processApiResponse(request = { topRatedRepo.getTopRated() }, onSuccess = {
            it?.let { response->
                topRated.value = response
            }

        }, onFailure = {

        }, progressType = ProgressTypes.FULL_PROGRESS)
    }
}