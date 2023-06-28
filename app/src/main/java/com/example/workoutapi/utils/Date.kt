package com.example.workoutapi.utils

import java.text.SimpleDateFormat

class Date (private val date : String) {

    fun formatToDayMonthYear(): String {
        val inputDateFormat = SimpleDateFormat("yyyy-MM-dd")
        val outputDateFormat = SimpleDateFormat("EEE, MMM d, ''yy")

        val newDate = inputDateFormat.parse(date)

        val outputDate = outputDateFormat.format(newDate);

        return outputDate.toString()

    }

}