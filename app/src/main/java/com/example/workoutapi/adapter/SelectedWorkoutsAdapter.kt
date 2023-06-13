package com.example.workoutapi.adapter

import android.content.Context
import android.service.autofill.Dataset
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.workoutapi.R

class SelectedWorkoutsAdapter(val context : Context , val dataset: Dataset) : RecyclerView.Adapter<SelectedWorkoutsAdapter.ItemViewHolder>() {


    class ItemViewHolder(private val view : View) : RecyclerView.ViewHolder(view){

        val tvWorkoutName : TextView = view.findViewById(R.id.tv_selected_workout);

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}