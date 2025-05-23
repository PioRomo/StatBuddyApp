package com.example.statbuddy

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.statbuddy.ui.theme.StatBuddyTheme

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        installSplashScreen()
        setContent {
            StatBuddyTheme {
                LoginScreen { email, password ->
                    Log.d("Login", "Email: $email, Password: $password")
                    // TODO: Authenticate with Firebase or other backend
                }
            }
        }
    }
}
