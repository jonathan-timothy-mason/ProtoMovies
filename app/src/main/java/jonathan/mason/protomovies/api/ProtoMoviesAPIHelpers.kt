package jonathan.mason.protomovies.api

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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
        fun loadMovies(application: Application, movies: MutableLiveData<List<Movie>>) {
            // Call API function on separate thread.
            api.getMovies().enqueue(object: Callback<List<Movie>> {
                /**
                 * Handle [repsonse] of API [call] to load [Movie]s.
                 */
                override fun onResponse(call: Call<List<Movie>>, response: Response<List<Movie>>) {
                    val tempMovies =  response.body()
                    if(tempMovies != null && tempMovies.size > 0) {
                        movies.value = tempMovies
                    }
                }

                /**
                 * Handle [error] occurring during API [call].
                 */
                override fun onFailure(call: Call<List<Movie>>, error: Throwable) {
                    Toast.makeText(application, error.message, Toast.LENGTH_LONG).show()
                }
            })
        }
    }
}