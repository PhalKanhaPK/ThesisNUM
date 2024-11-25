package com.example.numthesis

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.widget.ActivityChooserView.InnerLayout

class AccountFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_account, container, false)

        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_account, container, false)
        val btnLogout = view.findViewById<Button>(R.id.btnLogout)

        btnLogout?.setOnClickListener {
            val intent = Intent(requireContext(), LoginActivity::class.java)
            startActivity(intent)

        }
        return view
    }
}