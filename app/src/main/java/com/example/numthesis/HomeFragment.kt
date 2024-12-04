package com.example.numthesis

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView

data class Generation(
    val genName: String = "" // Holds the generation name in Khmer
)

class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
//        val cardView = view.findViewById<CardView>(R.id.card1)
//        cardView?.setOnClickListener{
//            val groupFragment = ThesisFragment()
//           val transaction = parentFragmentManager.beginTransaction()
//           transaction.replace(R.id.fragmentContainerView, groupFragment)
//           transaction.addToBackStack(null)
//           transaction.commit()
//        }

        return view
    }
}