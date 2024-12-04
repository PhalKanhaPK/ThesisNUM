package com.example.numthesis

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
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

        database = FirebaseDatabase.getInstance().reference
        database.child("IT").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val generationList = mutableListOf<Generation>()
                if (snapshot.exists()) {
                    val generation = snapshot.getValue(Generation::class.java)
                    generation?.let { generationList.add(it) }
                }
                createCardViews(generationList)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("HomeFragment", "Failed to read value.", error.toException())
            }
        })
        return view


    }

    private fun createCardViews(generationList: List<Generation>) {
        val linearLayout = view?.findViewById<LinearLayout>(R.id.cardContainer)
        linearLayout?.removeAllViews()

        for (generation in generationList) {
            val cardView = LayoutInflater.from(context).inflate(R.layout.cardview_gen, linearLayout, false)
            val textView = cardView.findViewById<TextView>(R.id.genTextView)
            textView.text = generation.name

            linearLayout?.addView(cardView)
        }
    }
}