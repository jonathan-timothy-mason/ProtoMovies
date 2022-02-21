package jonathan.mason.protomovies

import android.content.Intent
import android.content.res.Configuration
import android.graphics.Rect
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import jonathan.mason.protomovies.api.EXTRA_MOVIE_ID
import jonathan.mason.protomovies.api.Movie
import jonathan.mason.protomovies.api.ProtoMoviesViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope

class HomeActivity : AppCompatActivity(), MoviesAdapter.MovieSelectionListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: ProtoMoviesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        this.setTitle(R.string.home_screen_caption)

        // Set up to use GridLayoutManager (movies adapter created after movies have loaded).
        var numberColumns = 2
        recyclerView = this.findViewById(R.id.recycler_view)
        recyclerView.setLayoutManager(GridLayoutManager(this, numberColumns))

        this.setupViewModel()

        // Now that search text and filter have been restored, load lunchbenches for the first time
        // since starting app, if necessary.
        if(!viewModel.isDataLoaded)
            viewModel.loadMovies()
    }

    /**
     * Setup up ViewModel to load and cache [Movie]s for lifetime of activity.
     */
    private fun setupViewModel() {
        viewModel = ViewModelProvider(this).get(ProtoMoviesViewModel::class.java)
        viewModel.getMovies().observe(this, { movies ->
            setRecyclerViewAdapter(movies)
        })
    }

    /**
     * Handle selection of Movie to show [IdentifierActivity] screen
     * for [selectedMovie].
     */
    override fun onMovieSelected(selectedMovie: Movie) {
        val intent = Intent(this, IdentifierActivity::class.java)
        intent.putExtra(EXTRA_MOVIE_ID, selectedMovie.id) // Pass selected WeatherProcess through Intent.
        startActivity(intent)
    }

    /**
     * Add [movies] to adapter and adapter to RecyclerView.
     */
    private fun setRecyclerViewAdapter(movies: List<Movie>) {
        // Set adapter of RecycleView (this causes it to update itself).
        recyclerView.setAdapter(MoviesAdapter(movies,this))
    }
}