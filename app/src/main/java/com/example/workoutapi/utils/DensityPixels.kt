package com.example.workoutapi.utils

import android.util.DisplayMetrics

class DensityPixels {

    companion object{


        fun convertPixelsToDp(dp: Int, metrics: DisplayMetrics): Float {

            val fPixels = metrics.density * dp

            return (fPixels + .5f);
        }
    }
}