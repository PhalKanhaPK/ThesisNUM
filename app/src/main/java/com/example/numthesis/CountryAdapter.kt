package com.example.numthesis
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

data class Country(val flagResId: Int, val name: String)

class CountryAdapter(context: Context, private val countries: List<Country>) :
    ArrayAdapter<Country>(context, 0, countries) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_country, parent, false)

        val country = countries[position]

        val flagImageView = view.findViewById<ImageView>(R.id.flagImageView)
        val countryNameTextView = view.findViewById<TextView>(R.id.countryNameTextView)

        flagImageView.setImageResource(country.flagResId)
        countryNameTextView.text = country.name

        return view
    }
}
