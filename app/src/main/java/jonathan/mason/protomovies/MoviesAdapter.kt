package jonathan.mason.protomovies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import jonathan.mason.protomovies.api.Movie

/**
 * Adapter for providing [Movie]s for [RecyclerView] from list [movies].
 * Notification of selection of [Movie] in [RecyclerView] can be provided by
 * [movieSelectionListener].
 */
class MoviesAdapter(val movies: List<Movie>, private val movieSelectionListener: MovieSelectionListener): RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {
    /**
     * Listener for notification of [Movie] selection.
     */
    interface MovieSelectionListener {
        /**
         * Handle selection of [selectedMovie].
         */
        fun onMovieSelected(selectedMovie: Movie)
    }

    /**
     * Create and return instance of [MovieViewHolder] class for adding
     * to [parent] ViewGroup; [viewType] is not used.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        // Dynamically create layout for item.
        val movie = LayoutInflater.from(parent.context).inflate(R.layout.movie, parent, false)
        return MovieViewHolder(movie)
    }

    /**
     * Bind supplied [viewHolder] to [Movie] at specified [position] in [movies]
     * list.
     */
    override fun onBindViewHolder(viewHolder: MovieViewHolder, position: Int) {
        viewHolder.Bind(position)
    }

    /**
     * Return total number of [Movie]s.
     */
    override fun getItemCount(): Int {
        return movies.size
    }

    /**
     * ViewHolder subclass for binding to [itemView] and display of associated [Movie].
     */
    inner class MovieViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private var movieImage: ImageView = itemView.findViewById(R.id.movie_image)
        private var movieDuration: TextView = itemView.findViewById(R.id.movie_duration)

        /**
         * Init block required to setup OnClickListener of View bound to ViewHolder.
         */
        init {
            itemView.setOnClickListener(this)
        }

        /**
         * Bind view holder to [Movie] at specified [position] in
         * [movies] list.
         */
        fun Bind(position: Int) {
            val movie = movies[position]
            if(movie.images.size > 0) {
                Picasso.get().load(movie.images[0].url).into(movieImage)
            }
            movieDuration.text = movie.duration
        }

        /**
         * Handle selection of ViewHolder to notify [movieSelectionListener] of adapter.
         */
        override fun onClick(view: View) {
            movieSelectionListener.onMovieSelected(movies[adapterPosition])
        }
    }
}