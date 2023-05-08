

package com.example.composeapp.movieApp.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.composeapp.movieApp.models.entities.Movie
import com.example.composeapp.movieApp.models.entities.Person
import com.example.composeapp.movieApp.models.entities.Tv
import com.example.composeapp.movieApp.persistence.MovieDao
import com.example.composeapp.movieApp.persistence.PeopleDao
import com.example.composeapp.movieApp.persistence.TvDao
import com.example.composeapp.movieApp.persistence.converters.IntegerListConverter
import com.example.composeapp.movieApp.persistence.converters.KeywordListConverter
import com.example.composeapp.movieApp.persistence.converters.ReviewListConverter
import com.example.composeapp.movieApp.persistence.converters.StringListConverter
import com.example.composeapp.movieApp.persistence.converters.VideoListConverter

@Database(
  entities = [(Movie::class), (Tv::class), (Person::class)],
  version = 3, exportSchema = false
)
@TypeConverters(
  value = [
    (StringListConverter::class), (IntegerListConverter::class),
    (KeywordListConverter::class), (VideoListConverter::class), (ReviewListConverter::class)
  ]
)
abstract class AppDatabase : RoomDatabase() {
  abstract fun movieDao(): MovieDao
  abstract fun tvDao(): TvDao
  abstract fun peopleDao(): PeopleDao
}
