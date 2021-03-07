package com.charlesawoodson.barparse.contents.databases

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.charlesawoodson.barparse.contents.databases.daos.TrackDao
import com.charlesawoodson.barparse.contents.responses.MusicGenres
import com.charlesawoodson.barparse.contents.responses.Track
import com.google.gson.Gson

@Database(entities = [Track::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class MusixMatchDatabase : RoomDatabase() {
    abstract fun trackDao(): TrackDao
}

class Converters {
    @TypeConverter
    fun genresToJson(value: MusicGenres): String = Gson().toJson(value)

    @TypeConverter
    fun jsonToGenres(value: String): MusicGenres = Gson().fromJson(value, MusicGenres::class.java)
}