package com.example.numthesis

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView

class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val cardView = view.findViewById<CardView>(R.id.card1)
        cardView?.setOnClickListener{
            // Create an instance of the fragment you want to display
            val groupFragment = GroupFragment()
           // Replace the current fragment with the new fragment
           val transaction = parentFragmentManager.beginTransaction()
           transaction.replace(R.id.fragmentContainerView, groupFragment) // Use the ID of your container
           transaction.addToBackStack(null) // Optional: Add to back stack to allow user to navigate back
           transaction.commit()
        }

        return view
    }
}