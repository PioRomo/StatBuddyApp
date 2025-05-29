package com.example.statbuddy

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.statbuddy.ui.theme.StatBuddyTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class RegisterActivity : ComponentActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        setContent {
            StatBuddyTheme {
                RegisterScreen(
                    onRegisterClick = { name, email, password ->
                        auth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    val userId = auth.currentUser?.uid
                                    val userMap = hashMapOf(
                                        "name" to name,
                                        "email" to email
                                    )

                                    if (userId != null) {
                                        firestore.collection("users")
                                            .document(userId)
                                            .set(userMap)
                                            .addOnSuccessListener {
                                                startActivity(Intent(this, HomeActivity::class.java))
                                                finish()
                                            }
                                            .addOnFailureListener { e ->
                                                Toast.makeText(
                                                    this,
                                                    "Error saving user data: ${e.message}",
                                                    Toast.LENGTH_LONG
                                                ).show()
                                            }
                                    } else {
                                        Toast.makeText(this, "User ID is null", Toast.LENGTH_LONG).show()
                                    }
                                } else {
                                    Toast.makeText(
                                        this,
                                        "Registration failed: ${task.exception?.message}",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            }
                    },
                    onNavigateToLogin = {
                        startActivity(Intent(this, LoginActivity::class.java))
                        finish()
                    }
                )
            }
        }
    }
}
