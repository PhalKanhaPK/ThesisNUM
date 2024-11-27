package com.example.numthesis

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ListAdapter
import android.widget.ListView
import android.widget.TextView

class AccountFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_account, container, false)
        val btnLogout = view.findViewById<Button>(R.id.btnLogout)
        btnLogout?.setOnClickListener {
            val intent = Intent(requireContext(), LoginActivity::class.java)
            startActivity(intent)
        }

        val countries = listOf(
            Country(R.drawable.baseline_account_circle_24, "ជំនាញ : IT"),
            Country(R.drawable.baseline_account_circle_24, "ជំនាន់ : ៣០"),
            Country(R.drawable.baseline_account_circle_24, "ក្រុម : ៤៥"),
            Country(R.drawable.baseline_account_circle_24, "លេខទូរស័ព្ទ : 0987654321"),
            Country(R.drawable.baseline_account_circle_24, "Gmail : example123@gmail.com")
        )

        val listView: ListView = view.findViewById(R.id.ListView_userInfo)
        val adapter = CountryAdapter(requireContext(), countries)
        listView.adapter = adapter

        return view
    }
}