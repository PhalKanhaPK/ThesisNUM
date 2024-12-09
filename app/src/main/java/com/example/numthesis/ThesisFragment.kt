package com.example.numthesis

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ThesisFragment : Fragment() {
    private lateinit var database: DatabaseReference
    val databaseUrl = "https://numthesis-81c96-default-rtdb.firebaseio.com/" // Replace with your Firebase Realtime Database URL

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_thesis, container, false)
        // Retrieve the submitted text
        val textGroup = arguments?.getString("submittedGroup")
        val genTextView: TextView = view.findViewById(R.id.genTextView)
        genTextView.text = textGroup
        database = FirebaseDatabase.getInstance(databaseUrl).reference

//        database.child(textGroup.toString()).addListenerForSingleValueEvent(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//
//            }
//            override fun onCancelled(error: DatabaseError) {
//                // Handle database error
//                Log.e("FirebaseError", "Error: ${error.message}")
//            }
//        })

        // Create a list of Thesis
        val ThesisList = listOf(
            dataThesis("ក្រុម ទី១","Mobile App"),
            dataThesis("ក្រុម ទី២","Web Developer"),
            dataThesis("កក្រុម ទី៣","Desktop Application"),
            dataThesis("ក្រុម ទី៤","Database"),
            dataThesis("ក្រុម ទី៥","Network"),
            dataThesis("ក្រុម ទី៦","Cyber Security"),
            dataThesis("ក្រុម ទី៧","IOS Deviloper"),
            dataThesis("ក្រុម ទី៨","C# .NET framwork"),
            dataThesis("ក្រុម ទី៩","ASP.NET")
        )

        val listView: ListView = view.findViewById(R.id.list_thesis)
        val adapter = GroupAdapter(requireContext(), ThesisList)
        listView.adapter = adapter
        return view
    }
}