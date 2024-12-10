package com.example.numthesis

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.numthesis.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val binding = ActivityRegisterBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    fun onLoginNowClicked(view: View) {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    fun onSignUpClicked(view: View) {
        val email = findViewById<EditText>(R.id.EmailEditText).text.toString()
        val password = findViewById<EditText>(R.id.PasswordEditText).text.toString()
        if (email.isNotEmpty() || password.isNotEmpty()) {
            Authentication.auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener{
                if(it.isSuccessful){
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                }
            }.addOnFailureListener{
                Toast.makeText(this, it.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }
}