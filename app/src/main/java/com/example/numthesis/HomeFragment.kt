package com.example.numthesis

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.example.numthesis.databinding.FragmentHomeBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

data class Generation(
    val genName: String = "" // Holds the generation name in Khmer
)

class HomeFragment : Fragment() {
    private lateinit var database: DatabaseReference
    private lateinit var cardContainer: LinearLayout // LinearLayout where the CardViews will be added
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        database = FirebaseDatabase.getInstance().reference

//        val cardView = view.findViewById<CardView>(R.id.card1)
//        cardView?.setOnClickListener{
//            val groupFragment = ThesisFragment()
//           val transaction = parentFragmentManager.beginTransaction()
//           transaction.replace(R.id.fragmentContainerView, groupFragment)
//           transaction.addToBackStack(null)
//           transaction.commit()
//        }

        // Inflate the layout for this fragment using ViewBinding
        val binding = FragmentHomeBinding.inflate(inflater, container, false)

        // Get the root view from binding
        val rootView = binding.root

        // Initialize your cardContainer (LinearLayout where CardViews will be added)
        cardContainer = rootView.findViewById(R.id.cardContainer)

        // Return the rootView as the fragment's view
        return rootView
    }
    private fun loadCardData() {
        // Query both the "Accounting" and "IT" categories from Firebase
        database.child("Accounting").get().addOnSuccessListener { accountingSnapshot ->
            if (accountingSnapshot.exists()) {
                val generations = accountingSnapshot.children
                for (generationSnapshot in generations) {
                    val genName = generationSnapshot.value.toString() // Khmer text for the generation name
                    createCardView(generationSnapshot.key ?: "", genName) // Create card view dynamically
                }
            }
        }.addOnFailureListener {
            Toast.makeText(context, "Failed to load Accounting data", Toast.LENGTH_SHORT).show()
        }

        // Same for IT
        database.child("IT").get().addOnSuccessListener { itSnapshot ->
            if (itSnapshot.exists()) {
                val generations = itSnapshot.children
                for (generationSnapshot in generations) {
                    val genName = generationSnapshot.value.toString() // Khmer text for the generation name
                    createCardView(generationSnapshot.key ?: "", genName) // Create card view dynamically
                }
            }
        }.addOnFailureListener {
            Toast.makeText(context, "Failed to load IT data", Toast.LENGTH_SHORT).show()
        }
    }

    private fun createCardView(genKey: String, genName: String) {
        // Dynamically create the CardView and populate it with the data
        val cardView = layoutInflater.inflate(R.layout.cardview_gen, null) as CardView
        val textView = TextView(context)
        textView.text = "$genKey: $genName"  // Example: Gen26: ជំនាន់ ទី២៦
        textView.textAlignment = View.TEXT_ALIGNMENT_CENTER
        textView.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
        textView.textSize = 10f

        // Optionally set the image (replace with your image resource or URL if necessary)
        val imgView = cardView.findViewById<ImageView>(R.id.ImgView)
        imgView.setImageResource(R.drawable.paper) // Replace with your image resource

        // Optionally apply margins and other styling
        val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        layoutParams.setMargins(16, 16, 16, 16) // Set margins for the card
        cardView.layoutParams = layoutParams

        // Add the dynamically created CardView to the LinearLayout container
        cardContainer.addView(cardView)
    }
}