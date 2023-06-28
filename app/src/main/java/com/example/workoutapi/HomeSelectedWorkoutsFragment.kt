package com.example.workoutapi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.workoutapi.adapter.SelectedWorkoutsAdapter
import com.example.workoutapi.database.workout.AppDatabase
import com.example.workoutapi.database.workout.entities.Workouts
import com.example.workoutapi.databinding.FragmentHomeSelectedWorkoutsBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class HomeSelectedWorkoutsFragment : Fragment() {

    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO)

    private var _binding : FragmentHomeSelectedWorkoutsBinding? = null

    private val workouts : MutableList<Workouts> = mutableListOf();

    private val groupedWorkouts : MutableList<MutableList<Workouts>> = mutableListOf();

    private val binding get() = _binding!!;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // load db

        // Inflate the layout for this fragment
        _binding = FragmentHomeSelectedWorkoutsBinding.inflate(inflater, container, false);

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val rvSelectedWorkouts : RecyclerView = binding.rvSelectedWorkouts

        coroutineScope.launch {
            val db  = AppDatabase.getDatabase(requireContext()).workoutData()
            val allWorkouts = db.getAll();
            workouts.clear()
            workouts.addAll(allWorkouts)
            groupWorkoutsByDate()
        }

        binding.btnViewWorkouts.setOnClickListener{

            val action  = HomeSelectedWorkoutsFragmentDirections.actionHomeSelectedWorkoutsFragmentToViewWorkoutsFragment()

            view.findNavController().navigate(action);

        }

        rvSelectedWorkouts.adapter = SelectedWorkoutsAdapter(requireContext(), groupedWorkouts);
        rvSelectedWorkouts.setHasFixedSize(false);
        super.onViewCreated(view, savedInstanceState)

    }

    fun groupWorkoutsByDate() {

        var workoutList = mutableListOf<Workouts>()

        var tempGroupWorkouts : MutableList<MutableList<Workouts>> = mutableListOf();

        for(workout in workouts.orEmpty() ) {

            if(workoutList.getOrNull(0)?.workoutDate == workout.workoutDate){

                workoutList.add(workout);

            } else {

                if(workoutList.size > 0){

                    tempGroupWorkouts.add(workoutList);
                    workoutList = mutableListOf()

                }
                workoutList.add(workout);

            }

        }

        if(workoutList.size > 0 ){

            tempGroupWorkouts.add(workoutList);

        }

        groupedWorkouts.clear();
        groupedWorkouts.addAll(tempGroupWorkouts)

    }


}