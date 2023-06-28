package com.example.workoutapi

import android.app.ActionBar.LayoutParams
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import com.example.workoutapi.database.workout.AppDatabase
import com.example.workoutapi.database.workout.entities.Workouts
import com.example.workoutapi.databinding.FragmentViewWorkoutBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat


class ViewWorkoutFragment : Fragment() {

    private val COMPLETED_TOAST = "Completed all workouts!";

    private var _binding : FragmentViewWorkoutBinding? = null

    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO)
    private val binding get() = _binding!!;

    private val PARAMS : String = "workoutDate"

    private  var workoutDate : String? = null

    private lateinit var formattedWorkoutDate : String ;

    private var _workouts : List<Workouts>? = null ;
    private val workouts get() = _workouts!!;

    private val checkBoxesCompletedWorkout = mutableListOf<CheckBox>()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        arguments?.let { it ->
            workoutDate = it.getString(PARAMS).toString()

            formattedWorkoutDate = formatDate(it.getString(PARAMS).toString());
        }



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentViewWorkoutBinding.inflate(inflater, container, false)



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)



        binding.tvWorkoutDateVWF.text = formattedWorkoutDate

        coroutineScope.launch {
            getWorkouts(workoutDate!!);
            createWorkoutViews()

        }

    }


    private fun getWorkouts(workoutDate : String) {

         _workouts = AppDatabase.getDatabase(requireContext()).workoutData().get(workoutDate)

    }

    private suspend fun createWorkoutViews() = withContext(Dispatchers.Main){

        val container = binding.clViewWorkoutInsideContainer

        workouts.forEachIndexed{index, it ->

            val linearLayoutContainer = ConstraintLayout(requireContext())

            val checkBox = CheckBox(requireContext())

            val constraintSet = ConstraintSet();

            val marginParams  = LayoutParams(LayoutParams.MATCH_PARENT)

            val workoutNameTV  = TextView(requireContext())
            val workoutSetsTV  = TextView(requireContext())
            val workoutRepsTV = TextView(requireContext())

            workoutNameTV.id = index + 1
            workoutRepsTV.id = index + 2
            workoutSetsTV.id = index + 3
            checkBox.id = index + 4

            workoutNameTV.textSize = 25f
            workoutSetsTV.textSize = 25f
            workoutRepsTV.textSize = 25f

            workoutNameTV.layoutParams = marginParams
            workoutSetsTV.layoutParams = marginParams
            workoutRepsTV.layoutParams = marginParams


            linearLayoutContainer.id = 0


            workoutNameTV.text = it.workoutName
            workoutRepsTV.text = it.reps.toString()
            workoutSetsTV.text = it.sets.toString()


            workoutNameTV.maxWidth = 500
            workoutNameTV.width = 500
            workoutNameTV.maxLines = 1
            workoutNameTV.canScrollHorizontally(1)



            linearLayoutContainer.addView(workoutNameTV)
            linearLayoutContainer.addView(workoutRepsTV)
            linearLayoutContainer.addView(workoutSetsTV)
            linearLayoutContainer.addView(checkBox)

            constraintSet.clone(linearLayoutContainer)

            constraintSet.connect(workoutNameTV.id, ConstraintSet.TOP, linearLayoutContainer.id, ConstraintSet.TOP);
            constraintSet.connect(workoutNameTV.id, ConstraintSet.LEFT, linearLayoutContainer.id, ConstraintSet.LEFT);

            constraintSet.connect(checkBox.id, ConstraintSet.TOP, linearLayoutContainer.id, ConstraintSet.TOP)
            constraintSet.connect(checkBox.id, ConstraintSet.RIGHT, linearLayoutContainer.id, ConstraintSet.RIGHT)

            constraintSet.connect(workoutRepsTV.id, ConstraintSet.TOP, linearLayoutContainer.id, ConstraintSet.TOP)
            constraintSet.connect(workoutRepsTV.id, ConstraintSet.RIGHT, checkBox.id, ConstraintSet.LEFT, 50)

            constraintSet.connect(workoutSetsTV.id, ConstraintSet.TOP, linearLayoutContainer.id, ConstraintSet.TOP)
            constraintSet.connect(workoutSetsTV.id, ConstraintSet.RIGHT, workoutRepsTV.id, ConstraintSet.LEFT, 50)

            constraintSet.applyTo(linearLayoutContainer)

            container.addView(linearLayoutContainer);

            checkBoxesCompletedWorkout.add(checkBox);

            checkBox.setOnClickListener{
                if(allCheckBoxesChecked()){
                    showToast()
                }
            }

        }

    }

    private fun formatDate(date : String) : String {


        val inputDateFormat = SimpleDateFormat("yyyy-MM-dd")
        val outputDateFormat = SimpleDateFormat("EEE, MMM d, ''yy")

        val newDate = inputDateFormat.parse(date)

        val outputDate = outputDateFormat.format(newDate);

        return outputDate.toString()

    }

    private fun allCheckBoxesChecked() : Boolean {

        var allChecked = true;

        checkBoxesCompletedWorkout.forEach{
           if(!it.isChecked){
               allChecked = false;
           }
        }

        return allChecked
    }

    private fun showToast() {
        val toast = Toast.makeText(requireContext(), COMPLETED_TOAST, Toast.LENGTH_SHORT)
        toast.show()
    }

}