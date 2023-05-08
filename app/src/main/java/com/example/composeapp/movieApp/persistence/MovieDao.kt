

package com.example.composeapp.movieApp.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.composeapp.movieApp.models.entities.Movie

@Dao
interface MovieDao {
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertMovieList(movies: List<Movie>)

  @Update
  fun updateMovie(movie: Movie)

  @Query("SELECT * FROM MOVIE WHERE id = :id_")
  fun getMovie(id_: Long): Movie

  @Query("SELECT * FROM Movie WHERE page = :page_")
   fun getMovieList(page_: Int): List<Movie>
}
