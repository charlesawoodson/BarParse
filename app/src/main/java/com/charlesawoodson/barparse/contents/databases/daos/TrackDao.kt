package com.charlesawoodson.barparse.contents.databases.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.charlesawoodson.barparse.contents.model.Track

@Dao
interface TrackDao {

    @Insert(onConflict = REPLACE)
    suspend fun saveTracks(tracks: List<Track>)

    @Insert(onConflict = REPLACE)
    suspend fun saveTrack(track: Track)

    @Query("SELECT * FROM tracks")
    fun getTracks(): List<Track>
}