

package com.example.composeapp.movieApp.ui.tv

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeapp.movieApp.repository.TvRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.shareIn
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class TvDetailViewModel @Inject constructor(
  private val tvRepository: TvRepository
) : ViewModel() {

  private val tvIdSharedFlow: MutableSharedFlow<Long> = MutableSharedFlow(replay = 1)

  val tvFlow = tvIdSharedFlow.flatMapLatest {
    tvRepository.loadTvById(it)
  }

  val videoListFlow = tvIdSharedFlow.flatMapLatest {
    tvRepository.loadVideoList(it)
  }.shareIn(viewModelScope, SharingStarted.WhileSubscribed(), replay = 1)

  val keywordListFlow = tvIdSharedFlow.flatMapLatest {
    tvRepository.loadKeywordList(it)
  }

  val reviewListFlow = tvIdSharedFlow.flatMapLatest {
    tvRepository.loadReviewsList(it)
  }

  init {
    Timber.d("Injection TvDetailViewModel")
  }

  fun fetchTvDetailsById(id: Long) = tvIdSharedFlow.tryEmit(id)
}
