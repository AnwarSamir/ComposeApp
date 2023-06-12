

package com.example.composeapp.movieApp.di

import com.example.composeapp.movieApp.network.service.MovieService
import com.example.composeapp.movieApp.network.service.PeopleService
import com.example.composeapp.movieApp.network.service.TheDiscoverService
import com.example.composeapp.movieApp.network.service.TopRatedService
import com.example.composeapp.movieApp.network.service.TvService
import com.example.composeapp.movieApp.persistence.MovieDao
import com.example.composeapp.movieApp.persistence.PeopleDao
import com.example.composeapp.movieApp.persistence.TvDao
import com.example.composeapp.movieApp.repository.DiscoverRepository
import com.example.composeapp.movieApp.repository.MovieRepository
import com.example.composeapp.movieApp.repository.PeopleRepository
import com.example.composeapp.movieApp.repository.TopRatedRepository
import com.example.composeapp.movieApp.repository.TvRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

  @Provides
  @ViewModelScoped
  fun provideDiscoverRepository(
    discoverService: TheDiscoverService,
    movieDao: MovieDao,
    tvDao: TvDao
  ): DiscoverRepository {
    return DiscoverRepository(discoverService, movieDao, tvDao)
  }

  @Provides
  @ViewModelScoped
  fun provideMovieRepository(
    movieService: MovieService,
    movieDao: MovieDao
  ): MovieRepository {
    return MovieRepository(movieService, movieDao)
  }

  @Provides
  @ViewModelScoped
  fun providePeopleRepository(
    peopleService: PeopleService,
    peopleDao: PeopleDao
  ): PeopleRepository {
    return PeopleRepository(peopleService, peopleDao)
  }

  @Provides
  @ViewModelScoped
  fun provideTvRepository(
    tvService: TvService,
    tvDao: TvDao
  ): TvRepository {
    return TvRepository(tvService, tvDao)
  }

  @Provides
  @ViewModelScoped
  fun provideTopRatedRepository(topRatedService: TopRatedService):TopRatedRepository{
    return TopRatedRepository(topRatedService)
  }
}
