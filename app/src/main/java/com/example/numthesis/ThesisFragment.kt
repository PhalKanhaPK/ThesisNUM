package com.example.numthesis

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ThesisFragment : Fragment() {
    private lateinit var database: DatabaseReference
    val databaseUrl = "https://numthesis-81c96-default-rtdb.firebaseio.com/" // Replace with your Firebase Realtime Database URL

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_thesis, container, false)
        // Retrieve the submitted text
        val textGen = arguments?.getString("submittedGen")
        val textMajor = arguments?.getString("submittedMajor")

        val genTextView: TextView = view.findViewById(R.id.genTextView)
        genTextView.text = textMajor+" : "+textGen
        database = FirebaseDatabase.getInstance(databaseUrl).reference
        val thesisList = ArrayList<dataThesis>()
        database.child(textMajor.toString()).child(textGen.toString()).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (groupSnapshot in snapshot.children) {
                    val groupName = groupSnapshot.key // e.g., "ក្រុមទី១"
                    val groupDes = groupSnapshot.getValue(String::class.java) // e.g., "java mobile"

                    if (groupName != null && groupDes != null) {
                        // Add data to the list
                        thesisList.add(dataThesis(groupName, groupDes))
                    }
                    val listView: ListView = view.findViewById(R.id.list_thesis)
                    val adapter = GroupAdapter(requireContext(), thesisList)
                    listView.adapter = adapter
                    // Add click listener to ListView
//                    listView.setOnItemClickListener  { _, _, position, _ ->
//                        // Get the clicked item
//                        val selectedItem = thesisList[position]
//
//                        // Open PDF viewer activity with file name
//                        val intent = Intent(requireContext(), PdfViewerActivity::class.java)
//                        intent.putExtra("PDF_FILE_NAME", "${selectedItem.title}.pdf")
//                        startActivity(intent)
//                    }
                }
            }
            override fun onCancelled(error: DatabaseError) {
                // Handle database error
                Log.e("FirebaseError", "Error: ${error.message}")
            }
        })
        return view
    }
}