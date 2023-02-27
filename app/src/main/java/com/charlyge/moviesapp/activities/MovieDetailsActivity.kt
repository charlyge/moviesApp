package com.charlyge.moviesapp.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.charlyge.moviesapp.R
import com.charlyge.moviesapp.databinding.ActivityMovieDetailsBinding
import com.squareup.picasso.Picasso

class MovieDetailsActivity : AppCompatActivity() {
    private lateinit var title :String
    private lateinit var id :String
    private lateinit var overview :String
    private lateinit var poster_path :String
    private lateinit var release_date :String

    private lateinit var binding :ActivityMovieDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        overview = intent.getStringExtra("overview").toString()
        poster_path = intent.getStringExtra("poster_path").toString()
        release_date = intent.getStringExtra("release_date").toString()
        title = intent.getStringExtra("title").toString()
        binding.overview.text = overview
       binding.releaseDateTv.text=  release_date

        Picasso.get().load(poster_path).placeholder(R.drawable.baseline_image_24)
            .into(binding.detailThumbnail)
       binding.releaseDate.text = release_date

    }
}