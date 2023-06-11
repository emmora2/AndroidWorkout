package com.example.workoutapi.spinner

import android.app.Activity
import android.view.View
import android.widget.AdapterView
import com.example.workoutapi.MainActivity

class SpinnerActivity(val callback : (String) -> Unit) : Activity(), AdapterView.OnItemSelectedListener {

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

        val workoutSelected = parent?.getItemAtPosition(position).toString();

        callback(workoutSelected);


    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}



