package com.example.workoutapi

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.workoutapi.adapter.SelectedWorkoutsAdapter
import com.example.workoutapi.database.workout.AppDatabase
import com.example.workoutapi.database.workout.entities.Workouts
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SelectedWorkoutsActivity : AppCompatActivity() {

    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO)

    private val context : Context = this;

    private val workouts : MutableList<Workouts> = mutableListOf();

    private val groupedWorkouts : MutableList<MutableList<Workouts>> = mutableListOf();

    private val selectedWorkoutsAdapter = SelectedWorkoutsAdapter(this, groupedWorkouts);

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_selected_workouts)

        val rvSelectedWorkouts : RecyclerView = findViewById(R.id.rv_selected_workouts);

        coroutineScope.launch {

                val db  = AppDatabase.getDatabase(context).workoutData()
                val allWorkouts = db.getAll();
                workouts.clear()
                workouts.addAll(allWorkouts)

                groupWorkoutsByDate()

        }

        rvSelectedWorkouts.adapter = selectedWorkoutsAdapter;
        rvSelectedWorkouts.setHasFixedSize(true);


    }


    fun groupWorkoutsByDate() {

        var workoutList = mutableListOf<Workouts>()

        for(workout in workouts.orEmpty() ) {

            if(workoutList.getOrNull(0)?.workoutDate == workout.workoutDate){
                // reset grouped workouts and add to
                workoutList.add(workout);
            } else {

                if(workoutList.size > 0){
                    groupedWorkouts.add(workoutList)
                    workoutList = mutableListOf()
                }
                workoutList.add(workout);
                // add date to end of workout list


            }

        }

        if(workoutList.size > 0 ){
            groupedWorkouts.add(workoutList);
        }

        workoutList = mutableListOf();

    }

}