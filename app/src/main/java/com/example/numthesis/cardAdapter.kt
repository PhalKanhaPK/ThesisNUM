package com.example.numthesis

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class cardAdapter(context: Context, private val data: List<String>) :
    ArrayAdapter<String>(context, 0, data) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(
            R.layout.cardview_gen,
            parent,
            false
        )
        val textView: TextView = view.findViewById(R.id.genTextView)
        textView.text = data[position]
        return view
    }
}