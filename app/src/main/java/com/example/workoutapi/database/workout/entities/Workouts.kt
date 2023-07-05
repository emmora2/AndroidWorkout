package com.example.workoutapi.database.workout.entities

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Workouts(@PrimaryKey val id: Int?,
                    @NonNull @ColumnInfo(name = "date") val workoutDate: String,
                    @NonNull @ColumnInfo(name = "workoutId") val workoutId : Int,
                    @NonNull @ColumnInfo(name ="name") val workoutName: String,
                    @NonNull @ColumnInfo(name ="reps") val reps: Int,
                    @NonNull @ColumnInfo(name ="sets") val sets: Int,
                    @NonNull @ColumnInfo(name ="completed") val completed: Boolean = false,
)