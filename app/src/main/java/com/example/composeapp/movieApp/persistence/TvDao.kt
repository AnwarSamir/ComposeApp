

package com.example.composeapp.movieApp.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.composeapp.movieApp.models.entities.Tv

@Dao
interface TvDao {
  @Insert(onConflict = OnConflictStrategy.REPLACE)
   fun insertTv(tvs: List<Tv>)

  @Update
   fun updateTv(tv: Tv)

  @Query("SELECT * FROM Tv WHERE id = :id_")
   fun getTv(id_: Long): Tv?

  @Query("SELECT * FROM Tv WHERE page = :page_")
   fun getTvList(page_: Int): List<Tv>
}
