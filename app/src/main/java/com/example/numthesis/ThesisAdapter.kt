package com.example.numthesis
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

data class dataThesis(val title: String, val des: String)

class GroupAdapter(context: Context, private val thesis: List<dataThesis>) :
    ArrayAdapter<dataThesis>(context, 0, thesis) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_thesis, parent, false)

        val thesisPo = thesis[position]

        val iconView = view.findViewById<ImageView>(R.id.iconView)
        val titleTextView = view.findViewById<TextView>(R.id.title)
        val  desTextView = view.findViewById<TextView>(R.id.des)

        iconView.setImageResource(R.drawable.pdf)
        titleTextView.text = thesisPo.title
        desTextView.text = thesisPo.des

        return view
    }
}
