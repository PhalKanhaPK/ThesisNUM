package com.example.numthesis

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.cardview.widget.CardView

class cardAdapter(context: Context, private val data: List<String>, private val onCardClick: (String) -> Unit) :
    ArrayAdapter<String>(context, 0, data) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(
            R.layout.cardview_gen,
            parent,
            false
        )
        val textView: TextView = view.findViewById(R.id.genTextView)
        val cardView: CardView = view.findViewById(R.id.genCardView)
        textView.text = data[position]
        // Set the click listener for the CardView
        cardView.setOnClickListener {
            // Pass the text to the callback
            onCardClick(textView.text.toString())
        }
        return view
    }
}