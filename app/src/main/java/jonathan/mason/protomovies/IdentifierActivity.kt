package jonathan.mason.protomovies

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import jonathan.mason.protomovies.api.EXTRA_MOVIE_ID
import jonathan.mason.protomovies.databinding.ActivityIdentifierBinding

class IdentifierActivity : AppCompatActivity() {

    private lateinit var binding: ActivityIdentifierBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ActivityIdentifierBinding is auto-generated binding class based upon name
        // of actual resource layout activity_login.xml.
        binding = ActivityIdentifierBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        this.setTitle(R.string.identifier_screen_caption)

        // Display ID passed is intent.
        if (this.intent.hasExtra(EXTRA_MOVIE_ID)) {
            val n = this.intent.getIntExtra(EXTRA_MOVIE_ID, -1)
            if(n != -1) {
                binding.idText.text = n.toString()
            }

        }
    }
}