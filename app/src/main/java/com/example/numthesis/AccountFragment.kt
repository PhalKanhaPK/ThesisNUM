package com.example.numthesis

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ListView

class AccountFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_account, container, false)
        val btnLogout = view.findViewById<Button>(R.id.btnLogout)
        btnLogout?.setOnClickListener {
            val intent = Intent(requireContext(), LoginActivity::class.java)
            startActivity(intent)
        }

        val UserInfo = listOf(
            Info(R.drawable.major, "ជំនាញ : IT"),
            Info(R.drawable.level, "ជំនាន់ : ៣០"),
            Info(R.drawable.group, "ក្រុម : ៤៥"),
            Info(R.drawable.phone, "លេខទូរស័ព្ទ : 0987654321"),
            Info(R.drawable.gmail, "Gmail : example123@gmail.com")
        )

        val listView: ListView = view.findViewById(R.id.ListView_userInfo)
        val adapter = UserInfoAdapter(requireContext(), UserInfo)
        listView.adapter = adapter
        return view
    }
}