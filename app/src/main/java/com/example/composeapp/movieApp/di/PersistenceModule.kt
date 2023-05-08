

package com.example.composeapp.movieApp.di

import android.content.Context
import androidx.room.Room
import com.example.composeapp.movieApp.persistence.AppDatabase
import com.example.composeapp.movieApp.persistence.MovieDao
import com.example.composeapp.movieApp.persistence.PeopleDao
import com.example.composeapp.movieApp.persistence.TvDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {

  @Provides
  @Singleton
  fun provideRoomDataBase(@ApplicationContext context: Context): AppDatabase {
    return Room
      .databaseBuilder(context, AppDatabase::class.java, "MovieCompose.db")
      .allowMainThreadQueries()
      .build()
  }

  @Provides
  @Singleton
  fun provideMovieDao(appDatabase: AppDatabase): MovieDao {
    return appDatabase.movieDao()
  }

  @Provides
  @Singleton
  fun provideTvDao(appDatabase: AppDatabase): TvDao {
    return appDatabase.tvDao()
  }

  @Provides
  @Singleton
  fun providePeopleDao(appDatabase: AppDatabase): PeopleDao {
    return appDatabase.peopleDao()
  }
}
