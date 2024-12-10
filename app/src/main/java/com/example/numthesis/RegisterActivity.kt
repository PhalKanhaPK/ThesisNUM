package com.example.numthesis

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.numthesis.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)

        binding.btnSignUp.setOnClickListener {
            val email = binding.EmailEditText.text.toString()
            val password = binding.PasswordEditText.text.toString()
            if (email.isNotEmpty() && password.isNotEmpty()) { // Ensure both are non-empty
                MainActivity.auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            startActivity(Intent(this, LoginActivity::class.java))
                            finish()
                        }
                    }.addOnFailureListener { exception ->
                        Toast.makeText(this, exception.localizedMessage, Toast.LENGTH_SHORT).show()
                    }
            } else {
                Toast.makeText(this, "Email and Password cannot be empty", Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun onLoginNowClicked(view: View) {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}