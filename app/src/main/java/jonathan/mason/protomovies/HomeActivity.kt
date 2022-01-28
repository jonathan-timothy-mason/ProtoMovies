package jonathan.mason.protomovies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import jonathan.mason.protomovies.api.ProtoMoviesViewModel

class HomeActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: ProtoMoviesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        this.setTitle(R.string.home_screen_caption)

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
            //recyclerView.adapter = MoviesAdapter(movies, this)
        })
    }
}