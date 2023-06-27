package com.example.workoutapi

import android.app.ActionBar.LayoutParams
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.compose.ui.text.style.TextAlign
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.marginRight
import androidx.core.view.setMargins
import androidx.room.Database
import com.example.workoutapi.database.workout.AppDatabase
import com.example.workoutapi.database.workout.entities.Workouts
import com.example.workoutapi.databinding.FragmentViewWorkoutBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.w3c.dom.Text

class ViewWorkoutFragment : Fragment() {

    private var _binding : FragmentViewWorkoutBinding? = null

    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO)
    private val binding get() = _binding!!;

    private val PARAMS : String = "workoutDate"

    private  var workoutDate : String? = null

    private var _workouts : List<Workouts>? = null ;
    private val workouts get() = _workouts!!;

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        arguments?.let { it ->
            workoutDate = it.getString(PARAMS);
        }
        coroutineScope.launch {
            getWorkouts(workoutDate!!);

        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentViewWorkoutBinding.inflate(inflater, container, false);



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        if(_workouts != null){
            createWorkoutViews();

        }

        binding.tvWorkoutDateVWF.text = workoutDate;


    }


    private fun getWorkouts(workoutDate : String) {

         _workouts = AppDatabase.getDatabase(requireContext()).workoutData().get(workoutDate)

    }


    private fun createWorkoutViews(){

        val container = binding.clViewWorkoutInsideContainer

        workouts.forEach{

            val linearLayoutContainer = LinearLayout(requireContext());

            linearLayoutContainer.orientation = LinearLayout.HORIZONTAL;

            linearLayoutContainer.setBackgroundColor(1000)


            val marginParams  = LayoutParams(LayoutParams.MATCH_PARENT);

            marginParams.setMargins(50);

            val workoutNameTV  = TextView(requireContext())
            val workoutSetsTV  = TextView(requireContext());
            val workoutRepsTV = TextView(requireContext());



            workoutNameTV.textSize = 25f
            workoutSetsTV.textSize = 25f;
            workoutRepsTV.textSize = 25f;

            workoutNameTV.layoutParams = marginParams;
            workoutSetsTV.layoutParams = marginParams;
            workoutRepsTV.layoutParams = marginParams


            workoutNameTV.text = it.workoutName
            workoutRepsTV.text = it.reps.toString()
            workoutSetsTV.text = it.sets.toString()


            linearLayoutContainer.addView(workoutNameTV)
            linearLayoutContainer.addView(workoutRepsTV)
            linearLayoutContainer.addView(workoutSetsTV)




            container.addView(linearLayoutContainer);



        }

    }

}