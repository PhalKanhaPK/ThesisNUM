package com.example.numthesis

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.widget.ActivityChooserView.InnerLayout

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

        val arrayAdapter: ArrayAdapter<*>
        val userInfo = arrayOf("ជំនាញ : IT","ជំនាន់ : ៣០","ក្រុម : ៤៥","លេខទូរស័ : 0987654321","Gmail : example@gmail.com")

        var mListView = view.findViewById<ListView>(R.id.ListView_userInfo)
        arrayAdapter = ArrayAdapter(requireContext(),android.R.layout.simple_list_item_1, userInfo)
        mListView.adapter = arrayAdapter

        return view
    }
}