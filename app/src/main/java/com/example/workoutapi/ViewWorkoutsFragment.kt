package com.example.workoutapi
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.workoutapi.adapter.WorkoutItemAdapter
import com.example.workoutapi.databinding.FragmentViewWorkoutsBinding
import com.example.workoutapi.models.WorkoutListViewModel
import com.example.workoutapi.models.Workouts
import com.example.workoutapi.network.WorkoutApi
import com.example.workoutapi.spinner.SpinnerActivity
import kotlinx.coroutines.runBlocking

class ViewWorkoutsFragment : Fragment() {

    private val sharedViewModel : WorkoutListViewModel by activityViewModels()

    private  var workoutData : MutableList<Workouts> = mutableListOf();

    private lateinit var workoutDataAdapter : WorkoutItemAdapter;

    private lateinit var spinner : Spinner;

    private var _binding : FragmentViewWorkoutsBinding? = null;

    private lateinit var recyclerView: RecyclerView;

    private val binding get() = _binding!!;
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentViewWorkoutsBinding.inflate(inflater, container, false);

        setSpinner();

        val view = binding.root;

        return view;
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        recyclerView = binding.workoutRecyclerView

        workoutDataAdapter = WorkoutItemAdapter(requireContext(), workoutData, sharedViewModel);

        recyclerView.adapter = workoutDataAdapter;

        recyclerView.setHasFixedSize(true);

        spinner.onItemSelectedListener = SpinnerActivity(::updateData);

        setBtnConfirmWorkoutListener()

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null;
    }

    private fun setSpinner() {

        spinner = binding.workoutSpinner

        ArrayAdapter.createFromResource(requireContext(), R.array.workout_muscle, android.R.layout.simple_spinner_item).also {

                adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter

        }

    }
    private fun updateData(newQuery : String)  {

        runBlocking {
            val workouts  =  WorkoutApi.retrofitService.getWorkout(newQuery)

            workoutData.clear()

            workoutData.addAll(workouts)

            workoutDataAdapter.notifyDataSetChanged()

        }

    }


    private fun setBtnConfirmWorkoutListener() {
        binding.btnViewSelectedWorkouts.setOnClickListener{
            findNavController().navigate(ViewWorkoutsFragmentDirections.actionViewWorkoutsFragmentToWorkoutConfirmation())
        }
    }

}

