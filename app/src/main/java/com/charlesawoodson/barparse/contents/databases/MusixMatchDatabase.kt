package com.charlesawoodson.barparse.contents.databases

import androidx.room.Database
import androidx.room.RoomDatabase
import com.charlesawoodson.barparse.contents.databases.daos.TrackDao
import com.charlesawoodson.barparse.contents.model.Track

@Database(entities = [Track::class], version = 1, exportSchema = false)
abstract class MusixMatchDatabase : RoomDatabase() {
    abstract fun trackDao(): TrackDao
}