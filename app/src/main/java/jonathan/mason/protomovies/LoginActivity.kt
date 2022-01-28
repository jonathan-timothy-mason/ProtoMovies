package jonathan.mason.protomovies

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

/**
 * Initial login screen.
 */
class LoginActivity : AppCompatActivity() {

    private lateinit var email: EditText
    private lateinit var password: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        this.setTitle(R.string.sign_in_screen_caption)

        email = this.findViewById(R.id.email)
        password = this.findViewById(R.id.password)
    }

    /**
     * Handle press of sign-in button to check credentials and if successful, launch home screen.
     */
    fun onSignIn(button: View) {
        // Just check if any text has been entered for password.
        if( password.text.isNullOrBlank()) {
            return
        }

        // Success launch home screen.
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }
}