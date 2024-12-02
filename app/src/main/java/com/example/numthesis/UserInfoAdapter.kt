package com.example.numthesis
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

data class Info(val flagResId: Int, val name: String)

class UserInfoAdapter(context: Context, private val Info: List<Info>) :
    ArrayAdapter<Info>(context, 0, Info) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_info, parent, false)

        val UserInfo = Info[position]

        val iconView = view.findViewById<ImageView>(R.id.iconView)
        val UserInfoTextView = view.findViewById<TextView>(R.id.UserInfoTextView)

        iconView.setImageResource(UserInfo.flagResId)
        UserInfoTextView.text = UserInfo.name

        return view
    }
}
