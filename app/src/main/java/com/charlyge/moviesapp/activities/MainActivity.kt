package com.charlyge.moviesapp.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.charlyge.moviesapp.adapters.AutoFitGridLayoutManager
import com.charlyge.moviesapp.adapters.MovieAdapter
import com.charlyge.moviesapp.databinding.ActivityMainBinding
import com.charlyge.moviesapp.models.MovieEntity
import com.charlyge.moviesapp.util.ProgressBarUtil
import com.charlyge.moviesapp.viewModel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity(), MovieAdapter.itemClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var moviesAdapter: MovieAdapter
    var movieEntities: List<MovieEntity>? = ArrayList()
    private val movieViewModel: MovieViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        movieViewModel.getPopularMovies()
        val progressBar = ProgressBarUtil().initProgressBar(this,"Please wait..","Fetching..")
        progressBar.show()
        movieViewModel.movieResponse.observe(this) {
            progressBar.dismiss()
            moviesAdapter.setMovieData(it.results)
        }
        moviesAdapter = MovieAdapter(this, movieEntities, this)


        binding.movieRecylerView.setHasFixedSize(true)
        movieViewModel.errorResponse.observe(this){
            progressBar.dismiss()
        }

        val layoutManager = AutoFitGridLayoutManager(this, 500)
        binding.movieRecylerView.layoutManager = layoutManager
        binding.movieRecylerView.adapter = moviesAdapter

    }

    override fun onItemClicked(
        id: String?,
        title: String?,
        release_date: String?,
        overview: String?,
        vote_average: String?,
        poster_path: String?,
        backDropImage: String?
    ) {
        startActivity(
            Intent(this, MovieDetailsActivity::class.java)
                .putExtra("id", id)
                .putExtra("title", title)
                .putExtra("overview", overview)
                .putExtra("poster_path", poster_path)
                .putExtra("release_date", release_date)

        )
    }
}