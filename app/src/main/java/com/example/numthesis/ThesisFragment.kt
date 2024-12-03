package com.example.numthesis

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView

class ThesisFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_thesis, container, false)

        val ThesisList = listOf(
            dataThesis(R.drawable.pdf, "ក្រុម ទី១","Mobile App"),
            dataThesis(R.drawable.pdf, "ក្រុម ទី២","Web Developer"),
            dataThesis(R.drawable.pdf, "កក្រុម ទី៣","Desktop Application"),
            dataThesis(R.drawable.pdf, "ក្រុម ទី៤","Database"),
            dataThesis(R.drawable.pdf, "ក្រុម ទី៥","Network"),
            dataThesis(R.drawable.pdf, "ក្រុម ទី៥","Network"),
            dataThesis(R.drawable.pdf, "ក្រុម ទី៥","Network"),
            dataThesis(R.drawable.pdf, "ក្រុម ទី៥","Network"),
            dataThesis(R.drawable.pdf, "ក្រុម ទី៥","Network")
        )

        val listView: ListView = view.findViewById(R.id.list_thesis)
        val adapter = GroupAdapter(requireContext(), ThesisList)
        listView.adapter = adapter

        return view
    }
}