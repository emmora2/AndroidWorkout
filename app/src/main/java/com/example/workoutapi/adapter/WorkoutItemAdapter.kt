package com.example.workoutapi.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.workoutapi.R
import com.example.workoutapi.ViewWorkoutsFragmentDirections
import com.example.workoutapi.models.SelectedWorkout
import com.example.workoutapi.models.WorkoutListViewModel
import com.example.workoutapi.models.Workouts

class WorkoutItemAdapter(private val context : Context, private val dataSet: List<Workouts>, private val workoutsViewModel : WorkoutListViewModel) : RecyclerView.Adapter<WorkoutItemAdapter.ItemViewHolder>() {

    private val REPS_SETS_NOT_SET_MSG = "Please enter a number for reps and sets";
    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        val textView : TextView = view.findViewById(R.id.tv_workout_name)
        val muscleGroupTextView : TextView = view.findViewById(R.id.tv_muscle_group)
        val addWorkoutBtn : Button = view.findViewById(R.id.btn_add_workout);
        val workoutReps : EditText = view.findViewById(R.id.et_workout_reps)
        val workoutSets : EditText = view.findViewById(R.id.et_workout_sets)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.workout_item, parent, false);
        return ItemViewHolder(adapterLayout);
    }

    override fun getItemCount() = dataSet.size;

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        val workout = dataSet[position];

        holder.textView.text = workout.muscle;

        holder.muscleGroupTextView.text = workout.name

        holder.addWorkoutBtn.setOnClickListener{

            if(verifyRepsSets(holder)){

                val workoutReps = Integer.parseInt(holder.workoutReps.text.toString())
                val workoutSets = Integer.parseInt(holder.workoutSets.text.toString())

                val workoutSelected = SelectedWorkout(workout.name, workoutReps, workoutSets, 1);
                workoutsViewModel.addWorkout(workoutSelected)

            } else {

                showMsgRepsSetsNotSet()
            }
        }

    }
    private fun verifyRepsSets(holder : ItemViewHolder) : Boolean {

        try {
             Integer.parseInt(holder.workoutReps.text.toString())
             Integer.parseInt(holder.workoutSets.text.toString())

        } catch (e : NumberFormatException) {
            return false;
        }
        return true;
    }


    private fun showMsgRepsSetsNotSet(){
        val toast = Toast.makeText(context, REPS_SETS_NOT_SET_MSG, Toast.LENGTH_SHORT)
        toast.show()
    }

}