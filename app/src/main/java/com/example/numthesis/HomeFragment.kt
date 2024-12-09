package com.example.numthesis

import android.os.Bundle
import android.text.TextUtils.replace
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class HomeFragment : Fragment() {
    //    private lateinit var database:  DatabaseReference
    private lateinit var database: DatabaseReference
    val databaseUrl = "https://numthesis-81c96-default-rtdb.firebaseio.com/" // Replace with your Firebase Realtime Database URL

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val itemList = ArrayList<String>() // Replace String with your data type
        //database = FirebaseDatabase.getInstance().reference
        database = FirebaseDatabase.getInstance(databaseUrl).reference
        // Read data from Firebase for major IT
        database.child("IT").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                itemList.clear() // Clear the list before adding new data
                for (childSnapshot in snapshot.children) {
                    val value = childSnapshot.getValue(String::class.java) // Replace String with your data type
                    value?.let { itemList.add(it) } // Add the non-null value to the list
                }
                // Now itemList contains the data from the "IT" node
                val linearLayout: LinearLayout = view.findViewById(R.id.ITCardContainer)
                val adapter = cardAdapter(requireContext(), itemList)
                {
                        clickedText -> navigateToThesisFragment(clickedText)
                }
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
        // Read data from Firebase for major Accounting
        database.child("Accounting").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                itemList.clear() // Clear the list before adding new data
                for (childSnapshot in snapshot.children) {
                    val value = childSnapshot.getValue(String::class.java) // Replace String with your data type
                    value?.let { itemList.add(it) } // Add the non-null value to the list
                }
                // Now itemList contains the data from the "Accounting" node
                val linearLayout: LinearLayout = view.findViewById(R.id.AccCardContainer)
                val adapter = cardAdapter(requireContext(), itemList)
                {
                        clickedText -> navigateToThesisFragment(clickedText)
                }
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
        // Read data from Firebase for major Management
        database.child("Management").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                itemList.clear() // Clear the list before adding new data
                for (childSnapshot in snapshot.children) {
                    val value = childSnapshot.getValue(String::class.java) // Replace String with your data type
                    value?.let { itemList.add(it) } // Add the non-null value to the list
                }
                // Now itemList contains the data from the "Management" node
                val linearLayout: LinearLayout = view.findViewById(R.id.ManCardContainer)
                val adapter = cardAdapter(requireContext(), itemList)
                {
                        clickedText -> navigateToThesisFragment(clickedText)
                }
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
        // Read data from Firebase for major Management
        database.child("Robot").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                itemList.clear() // Clear the list before adding new data
                for (childSnapshot in snapshot.children) {
                    val value = childSnapshot.getValue(String::class.java) // Replace String with your data type
                    value?.let { itemList.add(it) } // Add the non-null value to the list
                }
                // Now itemList contains the data from the "Robot" node
                val linearLayout: LinearLayout = view.findViewById(R.id.RoCardContainer)
                val adapter = cardAdapter(requireContext(), itemList)
                {
                    clickedText -> navigateToThesisFragment(clickedText)
                }
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
    private fun navigateToThesisFragment(text: String) {
        val thesisFragment = ThesisFragment()
        val bundle = Bundle().apply {
            putString("submittedGroup", text)
        }
        thesisFragment.arguments = bundle

        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, thesisFragment) // Replace with your container ID
            .addToBackStack(null)
            .commit()
    }
}