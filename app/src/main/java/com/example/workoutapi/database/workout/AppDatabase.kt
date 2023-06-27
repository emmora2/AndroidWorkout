package com.example.workoutapi.database.workout

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.workoutapi.database.workout.entities.Workouts


@Database(entities = [Workouts::class], version = 3)
abstract class AppDatabase : RoomDatabase() {

    abstract fun workoutData(): WorkoutsDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null;

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "app_database").fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }

    }

}