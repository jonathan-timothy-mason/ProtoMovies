package jonathan.mason.protomovies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class IdentifierActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_identifier)
        this.setTitle(R.string.identifier_screen_caption)
    }
}