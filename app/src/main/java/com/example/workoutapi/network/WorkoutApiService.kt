package com.example.workoutapi.network

import com.example.workoutapi.models.Workouts
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query


private val BASE_URL = "https://exercises-by-api-ninjas.p.rapidapi.com";

private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build();

private val retrofit = Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(moshi)).baseUrl(
    BASE_URL).build()


interface WorkoutApiService {

    @Headers("X-RapidAPI-Key: eb261a2c12msh73c6f5aab29ec56p165146jsn5c48627a2a91" , "X-RapidAPI-Host': 'exercises-by-api-ninjas.p.rapidapi.com")
    @GET("/v1/exercises")
    suspend fun getWorkout(@Query("muscle") workout: String) : MutableList<Workouts>
}


object WorkoutApi {

    val retrofitService : WorkoutApiService by lazy {
        retrofit.create(WorkoutApiService::class.java)
    }
}