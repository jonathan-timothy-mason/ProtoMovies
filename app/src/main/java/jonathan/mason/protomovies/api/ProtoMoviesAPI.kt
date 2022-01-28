package jonathan.mason.protomovies.api

import retrofit2.Call
import retrofit2.http.*

/**
 * Interface of Movie API, for use with Retrofit.
 */
interface ProtoMoviesAPI {
    /**
     * Get [Movie]s from endpoint.
     */
    @GET("interview")
    fun getMovies(): Call<List<Movie>>
}

