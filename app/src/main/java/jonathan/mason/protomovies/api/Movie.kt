package jonathan.mason.protomovies.api

/**
 * [Movie] object for use with API.
 *
 * [id] of [Movie].
 * [title] of [Movie].
 * [description] of [Movie].
 * [imageURLs] of [Movie].
 */
class Movie(var id: Int = -1, var title: String = "", var description: String = "", var imageURLs: Array<String> = arrayOf<String>()) { }