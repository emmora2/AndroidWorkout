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


    }

    }




