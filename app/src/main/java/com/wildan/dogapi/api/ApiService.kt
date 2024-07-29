package com.wildan.dogapi.api

// ApiService.kt
import com.wildan.dogapi.model.DogImage
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers


interface ApiService {
    @Headers("x-api-key: live_OkTYhrWTq2q1hLPvgGlGBzaJ5Nacz0DLYlUE7aRFd8RKgk3zKofheDDi0BNe9VdD")
    @GET("https://api.thedogapi.com/v1/images/search?breed_ids=213&limit=1")
    fun getRandomDogImage(): Call<List<DogImage>>
}

