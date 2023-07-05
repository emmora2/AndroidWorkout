package com.example.workoutapi

import android.os.Bundle
import android.view.Display
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout.LayoutParams
import android.widget.TextView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.activityViewModels
import com.example.workoutapi.databinding.FragmentWorkoutConfirmationBinding
import com.example.workoutapi.models.SelectedWorkout
import com.example.workoutapi.models.WorkoutListViewModel
import com.example.workoutapi.utils.DensityPixels


class WorkoutConfirmation : Fragment() {

    private val sharedViewModel : WorkoutListViewModel by activityViewModels()

    private var _binding :  FragmentWorkoutConfirmationBinding? = null

    private val binding get() = _binding!!;


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentWorkoutConfirmationBinding.inflate(inflater,container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        createTextViews()
    }


    private fun createTextViews() {

        val selectedWorkouts : List<SelectedWorkout> = sharedViewModel.workouts

        val displayMetrics = requireContext().resources.displayMetrics

        selectedWorkouts.forEach{

            val textView  = TextView(requireContext())

            val textViewRepsSets = TextView(requireContext());

            textView.text = it.name
            textViewRepsSets.text = "Sets ${it.reps}, Reps ${it.sets}"

            textView.textSize = DensityPixels.convertPixelsToDp(16, displayMetrics)
            textViewRepsSets.textSize = DensityPixels.convertPixelsToDp(8, displayMetrics)

            textView.width = LayoutParams.MATCH_PARENT
            textView.textAlignment = View.TEXT_ALIGNMENT_CENTER
            textViewRepsSets.width = LayoutParams.MATCH_PARENT
            textViewRepsSets.textAlignment = View.TEXT_ALIGNMENT_CENTER

            binding.llSelectedWorkoutsFWC.addView(textView);
            binding.llSelectedWorkoutsFWC.addView(textViewRepsSets);

        }
    }



}