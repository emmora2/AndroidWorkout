package com.example.workoutapi

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Database
import com.example.workoutapi.database.workout.AppDatabase
import com.example.workoutapi.database.workout.entities.Workouts
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SelectedWorkoutsActivity : AppCompatActivity() {

    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO)

    private val context : Context = this;

    private val workouts : MutableList<Workouts> = mutableListOf();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selected_workouts)

        val rvSelectedWorkouts: RecyclerView = findViewById(R.id.rv_selected_workouts);



        coroutineScope.launch {

            val db  = AppDatabase.getDatabase(context).workoutData()

            val allWorkouts = db.getAll();

            workouts.clear()
            workouts.addAll(allWorkouts)

        }

        //Todo get selected workouts from database sorted by date ascending


        //Todo group workouts by date


        // display data source to adapter

    }
}