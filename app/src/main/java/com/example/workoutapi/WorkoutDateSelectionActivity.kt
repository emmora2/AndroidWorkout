package com.example.workoutapi

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.CalendarView
import android.widget.CalendarView.OnDateChangeListener
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset

class WorkoutDateSelectionActivity : AppCompatActivity(), OnDateChangeListener {


    private lateinit var calendar : CalendarView;

    @RequiresApi(Build.VERSION_CODES.O)
    private var selectedWorkoutDate : LocalDate = LocalDate.now();

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workout_date_selection)

        val btnSave : Button = findViewById(R.id.btn_save_workout)

        this.calendar = findViewById(R.id.calendar_view_workout_date);

        val instant : Instant = Instant.ofEpochSecond(calendar.date)

        instant.atZone(ZoneId.systemDefault()).toLocalDate()


        calendar.setOnDateChangeListener(this)

        btnSave.setOnClickListener{

            println("seleted date is " + selectedWorkoutDate.toString())

            val mainActivityIntent = Intent(this, MainActivity::class.java)
            this.startActivity(mainActivityIntent);
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onSelectedDayChange(view: CalendarView, year: Int, month: Int, dayOfMonth: Int) {

        selectedWorkoutDate = LocalDate.of(year, month +1, dayOfMonth  );

    }


}

