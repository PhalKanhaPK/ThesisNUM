package com.example.numthesis

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView

class ThesisFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_thesis, container, false)
        // Retrieve the submitted text
        val submittedText = arguments?.getString("selectedText")

        val ThesisList = listOf(
            dataThesis(R.drawable.pdf, "ក្រុម ទី១","Mobile App"),
            dataThesis(R.drawable.pdf, "ក្រុម ទី២","Web Developer"),
            dataThesis(R.drawable.pdf, "កក្រុម ទី៣","Desktop Application"),
            dataThesis(R.drawable.pdf, "ក្រុម ទី៤","Database"),
            dataThesis(R.drawable.pdf, "ក្រុម ទី៥","Network"),
            dataThesis(R.drawable.pdf, "ក្រុម ទី៦","Cyber Security"),
            dataThesis(R.drawable.pdf, "ក្រុម ទី៧","IOS Deviloper"),
            dataThesis(R.drawable.pdf, "ក្រុម ទី៨","C# .NET framwork"),
            dataThesis(R.drawable.pdf, "ក្រុម ទី៩","ASP.NET")
        )

        val listView: ListView = view.findViewById(R.id.list_thesis)
        val adapter = GroupAdapter(requireContext(), ThesisList)
        listView.adapter = adapter

        // Retrieve the text passed as an argument
        val text = arguments?.getString("submitted_gen")
        // Example: Display the submitted text in a TextView
        val textView: TextView = view.findViewById(R.id.genTextView)
        textView.text = submittedText
        return view
    }
    companion object {
        fun newInstance(submittedText: String): ThesisFragment {
            val fragment = ThesisFragment()
            val args = Bundle()
            args.putString("submitted_gen", submittedText)
            fragment.arguments = args
            return fragment
        }
    }
}