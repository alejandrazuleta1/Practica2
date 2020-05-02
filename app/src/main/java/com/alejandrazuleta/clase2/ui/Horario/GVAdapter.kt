package com.alejandrazuleta.clase2.ui.Horario

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.alejandrazuleta.clase2.R
import com.alejandrazuleta.clase2.model.curso
import com.alejandrazuleta.clase2.model.sesion
import kotlinx.android.synthetic.main.itemgrid.view.*

class GVAdapter(context: Context, list: ArrayList<String>, cursosList: List<curso>, sesionList: List<sesion>) : BaseAdapter() {

    private var list = emptyList<String>()
    private var cursosList = emptyList<curso>()
    private var sesionList = emptyList<sesion>()
    private val context: Context

    init {
        this.list = list
        this.context = context
        this.cursosList = cursosList
        this.sesionList = sesionList
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val itemView = LayoutInflater.from(context).inflate(R.layout.itemgrid, parent, false)
        itemView.textView.text = list.get(position)

        if (list.get(position).isNotEmpty()){
            itemView.setOnClickListener{
                val intent = Intent(context, HorarioDetalleActivity::class.java)
                intent.putExtra("sesion",sesionList[position])
                intent.putExtra("curso",cursosList[position]).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(intent)
            }
        }
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