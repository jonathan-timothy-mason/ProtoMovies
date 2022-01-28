package jonathan.mason.protomovies

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import jonathan.mason.protomovies.api.EXTRA_MOVIE_ID

class IdentifierActivity : AppCompatActivity() {

    private lateinit var id: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_identifier)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        this.setTitle(R.string.identifier_screen_caption)

        // Display ID passed is intent.
        id = this.findViewById(R.id.id_text)
        if (this.intent.hasExtra(EXTRA_MOVIE_ID)) {
            val n = this.intent.getIntExtra(EXTRA_MOVIE_ID, -1)
            if(n != -1) {
                id.text = n.toString()
            }

        }
    }
}