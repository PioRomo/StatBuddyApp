package com.example.statbuddy

import android.os.Bundle
import android.content.Intent
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
                LoginScreen(
                    onLoginClick = { email, password ->
                        startActivity(Intent(this, HomeActivity::class.java))
                    },
                    onNavigateToRegister = {
                        startActivity(Intent(this, RegisterActivity::class.java))
                        finish()
                    }
                )
            }
        }
    }
}
