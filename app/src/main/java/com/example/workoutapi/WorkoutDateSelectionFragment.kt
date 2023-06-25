package com.example.workoutapi

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CalendarView
import android.widget.CalendarView.OnDateChangeListener
import androidx.navigation.findNavController
import com.example.workoutapi.database.workout.AppDatabase
import com.example.workoutapi.database.workout.entities.Workouts
import com.example.workoutapi.databinding.FragmentWorkoutDateSelectionBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [WorkoutDateSelectionFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WorkoutDateSelectionFragment : Fragment(), OnDateChangeListener {

    private var _binding :  FragmentWorkoutDateSelectionBinding?  = null;

    private val binding get() = _binding!!;

    private val workoutKey : String = "workoutName"

    private lateinit var calendar : CalendarView;

    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO)

    private val context : Context = requireContext();

    private var selectedWorkoutDate : LocalDate = LocalDate.now();

    private lateinit var selectedWorkoutText : String;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            selectedWorkoutText = it.getString(workoutKey).toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentWorkoutDateSelectionBinding.inflate(inflater,container, false)

        return binding.root;
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        val btnSave : Button = binding.btnSaveWorkout

        this.calendar = binding.calendarViewWorkoutDate

        calendar.setOnDateChangeListener(this)

        val instant : Instant = Instant.ofEpochSecond(calendar.date)

        instant.atZone(ZoneId.systemDefault()).toLocalDate()

        btnSave.setOnClickListener{

            insertWorkout(selectedWorkoutText, selectedWorkoutDate.toString())

            returnToMainActivity(view);
        }
    }

    override fun onSelectedDayChange(view: CalendarView, year: Int, month: Int, dayOfMonth: Int) {

        selectedWorkoutDate = LocalDate.of(year, month +1, dayOfMonth  );

    }

    private fun returnToMainActivity(view : View) {

        val action = WorkoutDateSelectionFragmentDirections.actionWorkoutDateSelectionFragmentToHomeSelectedWorkoutsFragment()

        view.findNavController().navigate(action);


    }

    private fun insertWorkout(workoutName : String, workoutDate : String) {

        coroutineScope.launch {

            val db : AppDatabase = AppDatabase.getDatabase(context);

            db.workoutData().insert(Workouts(null,workoutDate, workoutName))

        }
    }

}


