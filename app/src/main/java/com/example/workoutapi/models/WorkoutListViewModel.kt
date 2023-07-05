package com.example.workoutapi.models
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.workoutapi.database.workout.entities.Workouts

class WorkoutListViewModel : ViewModel() {

    private val _workouts =  mutableListOf<SelectedWorkout>();

    val workouts : MutableList<SelectedWorkout> = _workouts;

    fun addWorkout(workout : SelectedWorkout) {
        _workouts.add(workout)
    }




}