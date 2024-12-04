package com.example.numthesis

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
data class Department(
    val Gen26: String? = null,
    val Gen27: String? = null,
    val Gen28: String? = null,
    val Gen29: String? = null,
    val Gen30: String? = null
)
class TestActivity : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    private lateinit var displayTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        displayTextView = findViewById(R.id.displayTextView)

        // Initialize Firebase database reference
        database = FirebaseDatabase.getInstance().reference

        // Read data from Firebase
        database.child("Accounting").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val accountingData = snapshot.getValue(Department::class.java)
                    displayTextView.text = "Accounting: $accountingData"
                }
            }

            override fun onCancelled(error: DatabaseError) {
                displayTextView.text = "Error: ${error.message}"
            }
        })

        database.child("IT").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val itData = snapshot.getValue(Department::class.java)
                    displayTextView.append("\nIT: $itData")
                }
            }

            override fun onCancelled(error: DatabaseError) {
                displayTextView.append("\nError: ${error.message}")
            }
        })
    }

}