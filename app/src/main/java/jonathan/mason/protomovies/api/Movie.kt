package jonathan.mason.protomovies.api

/**
 * Key for storing state in bundles.
 */
const val EXTRA_MOVIE_ID = "EXTRA_MOVIE_ID"

/**
 * [Movie] object for use with API.
 *
 * [id] of [Movie].
 * [title] of [Movie].
 * [description] of [Movie].
 * [imageURLs] of [Movie].
 */
class Movie(var id: Int = -1, var title: String = "", var description: String = "", var duration: String = "", var releaseDate: String = "", var images: Array<Image> = arrayOf<Image>()) { }

/**
 * Images of [Movie].
 */
class Image(var url: String = "", var type: String = "")