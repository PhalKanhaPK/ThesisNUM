package com.example.numthesis

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.google.firebase.database.DatabaseReference

class HomeFragment : Fragment() {

    private lateinit var database:  DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

//        database = FirebaseDatabase.getInstance().reference
//        database.child("IT").addListenerForSingleValueEvent(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                val generationList = mutableListOf<Generation>()
//                if (snapshot.exists()) {
//                    val generation = snapshot.getValue(Generation::class.java)
//                    generation?.let { generationList.add(it) }
//                }
//                createCardViews(generationList)
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                Log.w("HomeFragment", "Failed to read value.", error.toException())
//            }
//        })

        val linearLayout: LinearLayout = view.findViewById(R.id.cardContainer)
        val items = listOf("ជំនាន់ ទី២៦", "ជំនាន់ ទី២៧", "ជំនាន់ ទី២៨", "ជំនាន់ ទី២៩", "ជំនាន់ ទី២៣០")
        val adapter = cardAdapter(requireContext(), items)
        for (i in 0 until adapter.count) {
            val view = adapter.getView(i, null, linearLayout)
            linearLayout.addView(view)
        }
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