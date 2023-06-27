package com.example.workoutapi.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.workoutapi.HomeSelectedWorkoutsFragmentDirections
import com.example.workoutapi.R
import com.example.workoutapi.ViewWorkoutsFragmentDirections
import com.example.workoutapi.database.workout.entities.Workouts
import com.google.android.material.card.MaterialCardView

class SelectedWorkoutsAdapter(val context : Context, private val dataset: MutableList<MutableList<Workouts>>) : RecyclerView.Adapter<SelectedWorkoutsAdapter.ItemViewHolder>() {


    class ItemViewHolder(private val view : View) : RecyclerView.ViewHolder(view){

        val selectedWorkoutsContainer : MaterialCardView = view.findViewById(R.id.selected_workout_cards_container)
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


        val dateString = workouts[0].workoutDate

        dateView.text = dateString

        setCardContainerClickListenerCard(holder, dateString)

        holder.selectedWorkouts.addView(dateView);

        workoutNames.forEach{element ->

            val textView  = TextView(holder.itemView.context);

            textView.text = element;

            holder.selectedWorkouts.addView(textView)

        }
    }


    private fun setCardContainerClickListenerCard(holder : ItemViewHolder, workoutDate : String){

        holder.selectedWorkoutsContainer.setOnClickListener{

            val action = HomeSelectedWorkoutsFragmentDirections.actionHomeSelectedWorkoutsFragmentToViewWorkoutFragment(workoutDate)
            holder.itemView.findNavController().navigate(action);
        }

    }
}