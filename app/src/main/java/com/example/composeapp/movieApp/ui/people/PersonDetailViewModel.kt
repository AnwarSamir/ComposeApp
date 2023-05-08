

package com.example.composeapp.movieApp.ui.people

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeapp.movieApp.models.network.PersonDetail
import com.example.composeapp.movieApp.repository.PeopleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.shareIn
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class PersonDetailViewModel @Inject constructor(
  private val peopleRepository: PeopleRepository
) : ViewModel() {

  private val personIdSharedFlow: MutableSharedFlow<Long> = MutableSharedFlow(replay = 1)

  val personFlow = personIdSharedFlow.flatMapLatest {
    peopleRepository.loadPersonById(it)
  }.shareIn(viewModelScope, SharingStarted.WhileSubscribed(), replay = 1)

  val personDetailFlow: Flow<PersonDetail?> = personIdSharedFlow.flatMapLatest {
    peopleRepository.loadPersonDetail(it) {
    }.shareIn(viewModelScope, SharingStarted.WhileSubscribed(), replay = 1)
  }

  init {
    Timber.d("Injection PersonDetailViewModel")
  }

  fun fetchPersonDetailsById(id: Long) = personIdSharedFlow.tryEmit(id)
}
