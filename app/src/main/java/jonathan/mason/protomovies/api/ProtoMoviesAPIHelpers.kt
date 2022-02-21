package jonathan.mason.protomovies.api

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception
import kotlin.coroutines.CoroutineContext

class ProtoMoviesAPIHelpers {
    companion object {
        private val api: ProtoMoviesAPI

        init {
            // Create instance of API.
            api = Retrofit.Builder().baseUrl("https://content-cache.watchcorridor.com/v6/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(ProtoMoviesAPI::class.java)
        }

        /**
         * Load all [Movie]s.
         */
        suspend fun loadMovies(application: Application, movies: MutableLiveData<List<Movie>>) {
            try {
                // Call API function on separate thread.
                var response = api.getMovies()
                val tempMovies = response.body()
                if (tempMovies != null && tempMovies.size > 0) {
                    movies.value = tempMovies
                }
            }
            catch (error: Exception)
            {
                Toast.makeText(application, error.message, Toast.LENGTH_LONG).show()
            }
        }
    }
}