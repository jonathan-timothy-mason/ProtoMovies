package jonathan.mason.protomovies

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import jonathan.mason.protomovies.databinding.ActivityLoginBinding

/**
 * Initial login screen.
 */
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ActivityLoginBinding is auto-generated binding class based upon name
        // of actual resource layout activity_login.xml.
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        this.setTitle(R.string.sign_in_screen_caption)
    }

    /**
     * Handle press of sign-in button to check credentials and if successful, launch home screen.
     */
    fun onSignIn(button: View) {
        // Just check if any text has been entered for password.
        if( binding.password.text.isNullOrBlank()) {
            return
        }

        // Success launch home screen.
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }
}