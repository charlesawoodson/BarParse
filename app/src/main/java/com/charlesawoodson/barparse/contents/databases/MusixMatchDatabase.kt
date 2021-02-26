package com.charlesawoodson.barparse.contents.databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.charlesawoodson.barparse.R
import com.charlesawoodson.barparse.contents.databases.daos.TrackDao
import com.charlesawoodson.barparse.contents.model.Track

@Database(entities = [Track::class], version = 1)
abstract class MusixMatchDatabase : RoomDatabase() {
    companion object {
        fun create(context: Context): MusixMatchDatabase {
            val databaseBuilder = Room.databaseBuilder(
                context,
                MusixMatchDatabase::class.java,
                context.getString(R.string.app_database_name)
            )
            return databaseBuilder.build()
        }
    }

    abstract fun trackDao(): TrackDao
}