package com.example.workoutapi.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.workoutapi.R
import com.example.workoutapi.ViewWorkoutsFragmentDirections
import com.example.workoutapi.models.Workouts

class WorkoutItemAdapter(private val context : Context, private val dataSet: List<Workouts>) : RecyclerView.Adapter<WorkoutItemAdapter.ItemViewHolder>() {

    private  val intentKey : String  = "workoutName";
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

        val item = dataSet[position];

        holder.textView.text = item.muscle;

        holder.muscleGroupTextView.text = item.name

        holder.addWorkoutBtn.setOnClickListener{

            val action = ViewWorkoutsFragmentDirections.actionViewWorkoutsFragmentToWorkoutDateSelectionFragment(workoutName = item.name);

            it.findNavController().navigate(action);

        }

    }


}