package com.example.workoutapi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.ArrayAdapter
import android.widget.Button

import android.widget.Spinner
import androidx.recyclerview.widget.RecyclerView
import com.example.workoutapi.adapter.WorkoutItemAdapter
import com.example.workoutapi.models.Workouts
import com.example.workoutapi.network.WorkoutApi
import com.example.workoutapi.spinner.SpinnerActivity

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.runBlocking


class MainActivity : AppCompatActivity() {

    private lateinit var spinner : Spinner;

    private  var workoutData : MutableList<Workouts> = mutableListOf();

    private lateinit var recyclerView : RecyclerView;

    private lateinit var workoutDataAdapter : WorkoutItemAdapter;

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        setSpinner();

        setViewWorkoutListener();

        recyclerView = findViewById(R.id.workout_recycler_view)

        workoutDataAdapter = WorkoutItemAdapter(this, workoutData);

        recyclerView.adapter = workoutDataAdapter;

        recyclerView.setHasFixedSize(true);

        spinner.onItemSelectedListener = SpinnerActivity(::updateData);

    }



    fun setSpinner() {

        spinner = findViewById(R.id.workout_spinner)

        ArrayAdapter.createFromResource(this, R.array.workout_muscle, android.R.layout.simple_spinner_item).also {
                adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

    }

     fun updateData(newQuery : String)  {

         runBlocking {
             val workouts  =  WorkoutApi.retrofitService.getWorkout(newQuery)

             workoutData.clear()

             workoutData.addAll(workouts)

             workoutDataAdapter.notifyDataSetChanged()

         }

     }


    fun setViewWorkoutListener() {

        val btnViewWorkouts : Button = findViewById(R.id.btn_view_workouts);

        btnViewWorkouts.setOnClickListener{

            val selectedWorkoutsIntent = Intent(this, SelectedWorkoutsActivity::class.java )

            this.startActivity(selectedWorkoutsIntent);
        }
    }



    }




