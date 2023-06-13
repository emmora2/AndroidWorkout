package com.example.workoutapi.adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.workoutapi.R
import com.example.workoutapi.models.Workouts
import com.example.workoutapi.WorkoutDateSelectionActivity

class WorkoutItemAdapter(private val context : Context, private val dataSet: List<Workouts>) : RecyclerView.Adapter<WorkoutItemAdapter.ItemViewHolder>() {

    private  val intentKey : String  = "workoutName";
    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val textView : TextView = view.findViewById(R.id.workout_list_item)
        val muscleGroupTextView : TextView = view.findViewById(R.id.muscle_group_tv)
        val addWorkoutBtn : Button = view.findViewById(R.id.btn_add_workout);
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.workout_item, parent, false);
        return ItemViewHolder(adapterLayout);
    }

    override fun getItemCount() = dataSet.size;

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        val item = dataSet[position];

        holder.textView.text = item.name;

        holder.muscleGroupTextView.text = item.muscle

        holder.addWorkoutBtn.setOnClickListener{

            val extras = Bundle();

            extras.putString(intentKey, item.name);
            val dateSelectionIntent = Intent(context, WorkoutDateSelectionActivity::class.java)
            dateSelectionIntent.putExtras(extras);
            context.startActivity(dateSelectionIntent);
        }

    }


}