package jonathan.mason.protomovies

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import jonathan.mason.protomovies.api.EXTRA_MOVIE_ID
import jonathan.mason.protomovies.api.Movie
import jonathan.mason.protomovies.api.ProtoMoviesViewModel
import jonathan.mason.protomovies.databinding.ActivityHomeBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class HomeActivity : AppCompatActivity(), MoviesAdapter.MovieSelectionListener, CoroutineScope by MainScope() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var viewModel: ProtoMoviesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ActivityHomeBinding is auto-generated binding class based upon name
        // of actual resource layout activity_login.xml.
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        this.setTitle(R.string.home_screen_caption)

        // Set up to use GridLayoutManager (movies adapter created after movies have loaded).
        var numberColumns = 2
        binding.recyclerView.setLayoutManager(GridLayoutManager(this, numberColumns))

        this.setupViewModel()

        launch {
            // Now that search text and filter have been restored, load lunchbenches for the first time
            // since starting app, if necessary.
            if (!viewModel.isDataLoaded)
                viewModel.loadMovies()
        }
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
        binding.recyclerView.setAdapter(MoviesAdapter(movies,this))
    }
}