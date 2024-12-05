package com.example.numthesis

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class HomeFragment : Fragment() {

    private lateinit var database:  DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val itemList = ArrayList<String>() // Replace String with your data type
        database = FirebaseDatabase.getInstance().reference
        database.child("IT").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                itemList.clear() // Clear the list before adding new data
                for (childSnapshot in snapshot.children) {
                    val value = childSnapshot.getValue(String::class.java) // Replace String with your data type
                    value?.let { itemList.add(it) } // Add the non-null value to the list
                }
                // Now itemList contains the data from the "IT" node
                val linearLayout: LinearLayout = view.findViewById(R.id.cardContainer)
//                val items = listOf("ជំនាន់ ទី២៦", "ជំនាន់ ទី២៧", "ជំនាន់ ទី២៨", "ជំនាន់ ទី២៩", "ជំនាន់ ទី៣០")
                val adapter = cardAdapter(requireContext(), itemList)
                for (i in 0 until adapter.count) {
                    val view = adapter.getView(i, null, linearLayout)
                    linearLayout.addView(view)
                }
            }
            override fun onCancelled(error: DatabaseError) {
                // Handle database error
                Log.e("FirebaseError", "Error: ${error.message}")
            }
        })
        return view
    }
}