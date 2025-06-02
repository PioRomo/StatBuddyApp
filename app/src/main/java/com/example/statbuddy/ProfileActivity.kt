package com.example.statbuddy

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.statbuddy.ui.theme.StatBuddyTheme
import com.google.firebase.auth.FirebaseAuth

class ProfileActivity : ComponentActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()

        val userName = auth.currentUser?.displayName ?: "User"

        setContent {
            StatBuddyTheme {
                ProfileScreen(
                    userName = userName,
                    onLogoutClick = {
                        auth.signOut()
                        val intent = Intent(this, LoginActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)
                    },
                    onTabSelected = { index ->
                        when (index) {
                            0 -> startActivity(Intent(this, InsightsActivity::class.java))
                            1 -> startActivity(Intent(this, HomeActivity::class.java))
                            2 -> {} // Already in Profile
                        }
                    },
                    selectedItem = 2
                )
            }
        }
    }
}
