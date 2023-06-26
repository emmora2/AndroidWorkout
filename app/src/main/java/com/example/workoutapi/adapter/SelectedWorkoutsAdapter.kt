package com.example.workoutapi.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.workoutapi.R
import com.example.workoutapi.database.workout.entities.Workouts

class SelectedWorkoutsAdapter(val context : Context, private val dataset: MutableList<MutableList<Workouts>>) : RecyclerView.Adapter<SelectedWorkoutsAdapter.ItemViewHolder>() {


    class ItemViewHolder(private val view : View) : RecyclerView.ViewHolder(view){

        val selectedWorkouts: LinearLayout = view.findViewById(R.id.selected_workouts);

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.selected_workouts, parent, false);

        return ItemViewHolder(adapterLayout);

    }

    override fun getItemCount() = dataset.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        holder.selectedWorkouts.removeAllViews()

        val workouts = dataset[position]

        val workoutNames = workouts.map { it.workoutName }

        val dateView  = TextView(holder.selectedWorkouts.context)

        dateView.text = workouts[0].workoutDate

        holder.selectedWorkouts.addView(dateView);

        workoutNames.forEach{element ->

            val textView  = TextView(holder.itemView.context);

            textView.text = element;

            holder.selectedWorkouts.addView(textView)

        }
    }
}