package com.alejandrazuleta.clase2.ui.Horario

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.alejandrazuleta.clase2.R
import kotlinx.android.synthetic.main.itemgrid.view.*

class GVAdapter(context: Context, list: ArrayList<String>) : BaseAdapter() {

    private var list = emptyList<String>()
    private val context: Context

    init {
        this.list = list
        this.context = context
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val itemView = LayoutInflater.from(context).inflate(R.layout.itemgrid, parent, false)
        itemView.textView.text = list.get(position)
        return itemView
    }

    override fun getItem(position: Int): Any {
        return list.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return list.size
    }
}