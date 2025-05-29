package com.example.statbuddy

import android.os.Bundle
import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.statbuddy.ui.theme.StatBuddyTheme
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import android.widget.Toast


class LoginActivity : ComponentActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        auth = FirebaseAuth.getInstance()

        if (auth.currentUser != null) {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
            return
        }

        enableEdgeToEdge()
        installSplashScreen()
        setContent {
            StatBuddyTheme {
                LoginScreen(
                    onLoginClick = { email, password ->
                        if (email.isBlank() || password.isBlank()) {
                            Toast.makeText(this, "Please enter both email and password", Toast.LENGTH_SHORT).show()
                        } else {
                            FirebaseAuth.getInstance()
                                .signInWithEmailAndPassword(email, password)
                                .addOnCompleteListener { task ->
                                    if (task.isSuccessful) {
                                        startActivity(Intent(this, HomeActivity::class.java))
                                        finish()
                                    } else {
                                        Toast.makeText(
                                            this,
                                            "Invalid email or password",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }
                        }
                    }
                    ,
                    onNavigateToRegister = {
                        startActivity(Intent(this, RegisterActivity::class.java))
                        finish()
                    }
                )

            }
        }
    }
}
