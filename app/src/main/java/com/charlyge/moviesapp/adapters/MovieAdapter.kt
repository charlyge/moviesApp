package com.charlyge.moviesapp.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.charlyge.moviesapp.R
import com.charlyge.moviesapp.models.MovieEntity
import com.squareup.picasso.Picasso

class MovieAdapter(
    private val mContext: Context,
    moviesList: List<MovieEntity>?,
    newItemClickListener: itemClickListener
) : RecyclerView.Adapter<MovieAdapter.MyViewHolder?>() {
    private var moviesList: List<MovieEntity>?
    private val newItemClickListener: itemClickListener

    init {
        this.moviesList = moviesList
        this.newItemClickListener = newItemClickListener
    }

    interface itemClickListener {
        fun onItemClicked(
            id: String?,
            title: String?,
            release_date: String?,
            overview: String?,
            vote_average: String?,
            poster_path: String?,
            backDropImage: String?
        )
    }

    @NonNull
    override fun onCreateViewHolder(@NonNull parent: ViewGroup, viewType: Int): MyViewHolder {
        val context: Context = parent.context
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.movie_list_item, parent, false)
        return MyViewHolder(view)
    }



    override fun onBindViewHolder(@NonNull holder: MyViewHolder, position: Int) {
        val movies: MovieEntity = moviesList!![position]
        val title: String = movies.title.toString()
       val release_date: String = movies.release_date.toString()
        val poster_path = "https://image.tmdb.org/t/p/w342/" + movies.backdrop_path
        holder.textView_title.text = title
        holder.textView_release_date.text = release_date
        Log.d("AlbumAdapter", poster_path)

        Picasso.get().load(poster_path).placeholder(R.drawable.baseline_image_24)
            .into(holder.imageView_thumb)

    }


    override fun getItemCount(): Int{
        return if (moviesList == null) {
            0
        } else {
            moviesList!!.size
        }
    }

    inner class MyViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var imageView_thumb: ImageView

       var textView_title: TextView
       var textView_release_date: TextView

        init {
            imageView_thumb = itemView.findViewById(R.id.iv_thumbnail)
            textView_release_date = itemView.findViewById(R.id.release_date)

            textView_title = itemView.findViewById(R.id.nameTv)
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            val movies: MovieEntity = moviesList!![adapterPosition]
            val id: String = movies.id.toString()
            val title: String = movies.title.toString()
            val release_date: String = movies.release_date.toString()
            val overView: String = movies.overview.toString()
            val vote_Average: String = movies.vote_average.toString()

            val db_backDropImage: String = movies.backdrop_path.toString()
            val backDropImage = "https://image.tmdb.org/t/p/w342$db_backDropImage"

            newItemClickListener.onItemClicked(
                id,
                title,
                release_date,
                overView,
                vote_Average,
                backDropImage,
                db_backDropImage
            )
        }
    }



    fun setMovieData(moviesList: List<MovieEntity>?) {
        this.moviesList = moviesList
        notifyDataSetChanged()
    }
}