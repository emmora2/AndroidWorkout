package com.example.workoutapi.database.workout

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.workoutapi.database.workout.entities.Workouts


@Dao
interface WorkoutsDao {

    @Query("SELECT * FROM workouts ORDER BY date ASC")
    fun getAll(): List<Workouts>

    @Query("SELECT * FROM workouts where date = :date")
    fun get(date : String): List<Workouts>

    @Insert
    fun insert(workouts: Workouts);


}