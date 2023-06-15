package com.example.workoutapi

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CalendarView
import android.widget.CalendarView.OnDateChangeListener
import androidx.appcompat.app.AppCompatActivity
import com.example.workoutapi.database.workout.AppDatabase
import com.example.workoutapi.database.workout.entities.Workouts
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

class WorkoutDateSelectionActivity : AppCompatActivity(), OnDateChangeListener {


    private val intentWorkoutKey : String = "workoutName"

    private lateinit var calendar : CalendarView;

    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO)

    private val context : Context = this;

    private var selectedWorkoutDate : LocalDate = LocalDate.now();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workout_date_selection)

        val btnSave : Button = findViewById(R.id.btn_save_workout)

        this.calendar = findViewById(R.id.calendar_view_workout_date);

        calendar.setOnDateChangeListener(this)

        val instant : Instant = Instant.ofEpochSecond(calendar.date)

        instant.atZone(ZoneId.systemDefault()).toLocalDate()

        val selectedWorkoutText = intent.extras?.getString(intentWorkoutKey).toString();

        btnSave.setOnClickListener{

            insertWorkout(selectedWorkoutText, selectedWorkoutDate.toString())

            returnToMainActivity();

        }

    }

    override fun onSelectedDayChange(view: CalendarView, year: Int, month: Int, dayOfMonth: Int) {

        selectedWorkoutDate = LocalDate.of(year, month +1, dayOfMonth  );

    }

    fun returnToMainActivity() {

        val mainActivityIntent = Intent(this, MainActivity::class.java)
        this.startActivity(mainActivityIntent);

    }

    fun insertWorkout(workoutName : String, workoutDate : String) {

        coroutineScope.launch {

            val db : AppDatabase = AppDatabase.getDatabase(context);

            db.workoutData().insert(Workouts(null,workoutDate, workoutName))

        }
    }


}

