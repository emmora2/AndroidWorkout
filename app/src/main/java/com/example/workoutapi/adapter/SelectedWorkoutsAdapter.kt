package com.example.workoutapi.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.workoutapi.R
import com.example.workoutapi.database.workout.entities.Workouts

class SelectedWorkoutsAdapter(val context : Context , val dataset: MutableList<MutableList<Workouts>>) : RecyclerView.Adapter<SelectedWorkoutsAdapter.ItemViewHolder>() {


    class ItemViewHolder(private val view : View) : RecyclerView.ViewHolder(view){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.selected_workouts, parent, false);
        return ItemViewHolder(adapterLayout);

    }

    override fun getItemCount() = dataset.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        val workouts = dataset[position]

        val workoutNames = workouts.map {it.workoutName}

        workoutNames.forEach{
            val textView : TextView = TextView(holder.itemView.context);
            textView.setText(it);

            val container : ViewGroup = holder.itemView.findViewById(R.id.cl_selected_workouts)

            container.addView(textView);

        }
    }
}