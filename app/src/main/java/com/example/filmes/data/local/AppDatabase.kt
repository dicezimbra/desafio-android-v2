package com.example.filmes.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.filmes.data.local.dao.MovieDao
import com.example.filmes.data.local.entity.MovieEntity
import com.example.filmes.data.local.entity.MovieTypeConverter

@Database(entities = [MovieEntity::class], version = 1)
@TypeConverters(MovieTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract val movieDAO: MovieDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            synchronized(this) {
                var instance: AppDatabase? = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context,
                        AppDatabase::class.java,
                        "app_database"
                    ).build()
                }

                return instance
            }
        }
    }
}