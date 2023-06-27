package com.example.workoutapi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.workoutapi.databinding.FragmentViewWorkoutBinding

class ViewWorkoutFragment : Fragment() {

    private var _binding : FragmentViewWorkoutBinding? = null

    private val binding get() = _binding!!;

    private val PARAMS : String = "workoutDate"

    private  var workoutDate : String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let { it ->

            workoutDate = it.getString(PARAMS);
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentViewWorkoutBinding.inflate(inflater, container, false);

        //TODO create textViews for all workouts



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvWorkoutDateVWF.text = workoutDate;
    }


    private suspend fun getWorkouts() {
        //TODO get db
        // query for all workouts with specific date
    }

}