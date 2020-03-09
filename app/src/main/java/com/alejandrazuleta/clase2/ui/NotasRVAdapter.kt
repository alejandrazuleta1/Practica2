package com.alejandrazuleta.clase2.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alejandrazuleta.clase2.R
import com.alejandrazuleta.clase2.model.cursoinscrito
import kotlinx.android.synthetic.main.item_notas_rv.view.*

class NotasRVAdapter(
    private val context: Context,
    private val cursosinsList: ArrayList<cursoinscrito>
) : RecyclerView.Adapter<NotasRVAdapter.NotasViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NotasViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_notas_rv, parent, false)
        return NotasViewHolder(
            itemView,
            context
        )
    }

    override fun getItemCount(): Int = cursosinsList.size

    override fun onBindViewHolder(
        holder: NotasViewHolder,
        position: Int
    ) {
        val cursoinscrito: cursoinscrito = cursosinsList[position]
        holder.bindCursoins(cursoinscrito)
    }

    class NotasViewHolder(
        itemView: View,
        context: Context
    ) : RecyclerView.ViewHolder(itemView) {
        fun bindCursoins(cursoinscrito: cursoinscrito) {
            //buscar el usuario actual para asignar nombre del curso
            itemView.tv_nombre_curso.text = cursoinscrito.idu //cambiar por el nombre del curso
            itemView.tv_acumulado.text = cursoinscrito.idu //cambiar por el acumulado
        }
    }
}