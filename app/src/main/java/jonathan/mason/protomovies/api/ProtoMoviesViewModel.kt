package jonathan.mason.protomovies.api

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

/**
 * ViewModel to load and persist [Movie]s across configuration changes.
 *
 * Based on "Lifecycle Aware Data Loading with Architecture Components" by Ian Lake:
 * https://medium.com/androiddevelopers/lifecycle-aware-data-loading-with-android-architecture-components-f95484159de4.
 */
class ProtoMoviesViewModel(application: Application) : AndroidViewModel(application) {
    var isDataLoaded: Boolean = false

    private val movies: MutableLiveData<List<Movie>> = MutableLiveData<List<Movie>>()
    /**
     * Get loaded [Movie]s.
     */
    fun getMovies(): LiveData<List<Movie>> {
        return movies
    }

    /**
     * Load all [Movie]s.
     */
    suspend fun loadMovies() {
        isDataLoaded = true
        ProtoMoviesAPIHelpers.loadMovies(this.getApplication(), movies)
    }
}