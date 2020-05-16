package com.alejandrazuleta.clase2.ui.Horario

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.alejandrazuleta.clase2.R
import com.alejandrazuleta.clase2.model.curso
import com.alejandrazuleta.clase2.model.sesion
import kotlinx.android.synthetic.main.itemgrid.view.*

class GVAdapter(context: Context, list: ArrayList<HashMap<String,Int>>, cursosList: List<curso>, sesionList: List<sesion>) : BaseAdapter() {

    private var list = emptyList<HashMap<String,Int>>()
    private var cursosList = emptyList<curso>()
    private var sesionList = emptyList<sesion>()
    private val context: Context

    init {
        this.list = list
        this.context = context
        this.cursosList = cursosList
        this.sesionList = sesionList
    }

    @SuppressLint("ResourceAsColor", "ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val itemView = LayoutInflater.from(context).inflate(R.layout.itemgrid, parent, false)
        itemView.textView.text = list.get(position).keys.elementAt(0)
        itemView.textView.setTextColor(Color.BLACK)
        when(list.get(position).values.elementAt(0)){
            0->itemView.setBackgroundColor(Color.TRANSPARENT)
            1->itemView.setBackgroundColor(Color.TRANSPARENT)
            2->itemView.setBackgroundColor(Color.parseColor("#FF9800"))
            3->itemView.setBackgroundColor(Color.parseColor("#FFC107"))
            4->itemView.setBackgroundColor(Color.parseColor("#CDDC39"))
            5->itemView.setBackgroundColor(Color.parseColor("#2196F3"))
            6->itemView.setBackgroundColor(Color.parseColor("#673AB7"))
            7->itemView.setBackgroundColor(Color.parseColor("#009688"))
            8->itemView.setBackgroundColor(Color.parseColor("#F44336"))
            9->itemView.setBackgroundColor(Color.parseColor("#8BC34A"))
        }


        if (list.get(position).keys.elementAt(0).isNotEmpty() && list.get(position).keys.elementAt(0).length>5){
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