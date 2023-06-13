package com.example.workoutapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView

class SelectedWorkoutsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selected_workouts)

        val rvSelectedWorkouts: RecyclerView = findViewById(R.id.rv_selected_workouts);


    }
}