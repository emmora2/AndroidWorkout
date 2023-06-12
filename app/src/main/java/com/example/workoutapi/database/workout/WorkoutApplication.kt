package com.example.workoutapi.database.workout

import android.app.Application

class WorkoutApplication : Application() {

    val database : AppDatabase by lazy {AppDatabase.getDatabase(this)}
}