package com.wildan.dogapi

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.wildan.dogapi.api.RetrofitClient
import com.wildan.dogapi.model.DogImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var dogImageView: ImageView
    private lateinit var dogNameTextView: TextView
    private lateinit var breedNameTextView: TextView
    private lateinit var breedGroupTextView: TextView
    private lateinit var lifeSpanTextView: TextView
    private lateinit var temperamentTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dogImageView = findViewById(R.id.dogImageView)
        dogNameTextView = findViewById(R.id.dogNameTextView)
        breedNameTextView = findViewById(R.id.breedNameTextView)
        breedGroupTextView = findViewById(R.id.breedGroupTextView)
        lifeSpanTextView = findViewById(R.id.lifeSpanTextView)
        temperamentTextView = findViewById(R.id.temperamentTextView)

        fetchDogImage()
    }

    private fun fetchDogImage() {
        val call = RetrofitClient.instance.getRandomDogImage()

        call.enqueue(object : Callback<List<DogImage>> {
            override fun onResponse(
                call: Call<List<DogImage>>,
                response: Response<List<DogImage>>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let { dogImages ->
                        if (dogImages.isNotEmpty()) {
                            val dogImage = dogImages[0]
                            Glide.with(this@MainActivity).load(dogImage.url).into(dogImageView)

                            if (dogImage.breeds.isNotEmpty()) {
                                val breed = dogImage.breeds[0]

                                // Menampilkan nama anjing (breed name)
                                dogNameTextView.text = "Dog Name: ${breed.name}"

                                breedNameTextView.text = "Breed: ${breed.bred_for}"
                                breedGroupTextView.text = "Breed Group: ${breed.breed_group ?: "N/A"}"
                                lifeSpanTextView.text = "Life Span: ${breed.life_span}"
                                temperamentTextView.text = "Temperament: ${breed.temperament}"
                            }
                        }
                    }
                }
            }
            override fun onFailure(call: Call<List<DogImage>>, t: Throwable) {
                Log.e("MainActivity", "Error fetching dog image", t)
            }
        })
    }
}