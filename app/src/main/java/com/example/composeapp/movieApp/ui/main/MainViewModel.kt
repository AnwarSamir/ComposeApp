

package com.example.composeapp.movieApp.ui.main

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.ImageLoader
import com.example.composeapp.movieApp.models.entities.Movie
import com.example.composeapp.movieApp.models.entities.Person
import com.example.composeapp.movieApp.models.entities.Tv
import com.example.composeapp.movieApp.models.network.NetworkState
import com.example.composeapp.movieApp.repository.DiscoverRepository
import com.example.composeapp.movieApp.repository.PeopleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
  val imageLoader: ImageLoader,
  private val discoverRepository: DiscoverRepository,
  private val peopleRepository: PeopleRepository
) : ViewModel() {

  private val _selectedTab: MutableState<MainScreenHomeTab> =
    mutableStateOf(MainScreenHomeTab.MOVIE)
  val selectedTab: State<MainScreenHomeTab> get() = _selectedTab

  private val _movieLoadingState: MutableState<NetworkState> = mutableStateOf(NetworkState.IDLE)
  val movieLoadingState: State<NetworkState> get() = _movieLoadingState

  val movies: State<MutableList<Movie>> = mutableStateOf(mutableListOf())
  val moviePageStateFlow: MutableStateFlow<Int> = MutableStateFlow(1)
  private val newMovieFlow = moviePageStateFlow.flatMapLatest {
    _movieLoadingState.value = NetworkState.LOADING
    discoverRepository.loadMovies(
      page = it,
      success = { _movieLoadingState.value = NetworkState.SUCCESS },
      error = { _movieLoadingState.value = NetworkState.ERROR }
    )
  }.shareIn(viewModelScope, SharingStarted.WhileSubscribed(), replay = 1)

  private val _tvLoadingState: MutableState<NetworkState> = mutableStateOf(NetworkState.IDLE)
  val tvLoadingState: State<NetworkState> get() = _tvLoadingState

  val tvs: State<MutableList<Tv>> = mutableStateOf(mutableListOf())
  val tvPageStateFlow: MutableStateFlow<Int> = MutableStateFlow(1)
  private val newTvFlow = tvPageStateFlow.flatMapLatest {
    _tvLoadingState.value = NetworkState.LOADING
    discoverRepository.loadTvs(
      page = it,
      success = { _tvLoadingState.value = NetworkState.SUCCESS },
      error = { _tvLoadingState.value = NetworkState.ERROR }
    )
  }.shareIn(viewModelScope, SharingStarted.WhileSubscribed(), replay = 1)

  private val _personLoadingState: MutableState<NetworkState> = mutableStateOf(NetworkState.IDLE)
  val personLoadingState: State<NetworkState> get() = _personLoadingState

  val people: State<MutableList<Person>> = mutableStateOf(mutableListOf())
  val peoplePageStateFlow: MutableStateFlow<Int> = MutableStateFlow(1)

  private val newPeople = peoplePageStateFlow.flatMapLatest {
    _personLoadingState.value = NetworkState.LOADING
    peopleRepository.loadPeople(
      page = it,
      success = { _personLoadingState.value = NetworkState.SUCCESS },
      error = { _personLoadingState.value = NetworkState.ERROR }
    )
  }.shareIn(viewModelScope, SharingStarted.WhileSubscribed(), replay = 1)

  init {
    viewModelScope.launch(Dispatchers.IO) {
      newMovieFlow.collectLatest {
        movies.value.addAll(it)
      }
    }

    viewModelScope.launch(Dispatchers.IO) {
      newTvFlow.collectLatest {
        tvs.value.addAll(it)
      }
    }

    viewModelScope.launch(Dispatchers.IO) {
      newPeople.collectLatest {
        people.value.addAll(it)
      }
    }
  }

  fun selectTab(tab: MainScreenHomeTab) {
    _selectedTab.value = tab
  }

  fun fetchNextMoviePage() {
    if (movieLoadingState.value != NetworkState.LOADING) {
      moviePageStateFlow.value++
    }
  }

  fun fetchNextTvPage() {
    if (tvLoadingState.value != NetworkState.LOADING) {
      tvPageStateFlow.value++
    }
  }

  fun fetchNextPeoplePage() {
    if (personLoadingState.value != NetworkState.LOADING) {
      peoplePageStateFlow.value++
    }
  }
}
