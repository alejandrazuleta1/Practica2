package com.alejandrazuleta.clase2.ui.Eventos.recordatorios

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alejandrazuleta.clase2.R
import com.alejandrazuleta.clase2.model.cursoinscrito
import com.alejandrazuleta.clase2.model.evento
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.item_notas_rv.view.*
import kotlinx.android.synthetic.main.item_recordatorios.view.*
import java.math.RoundingMode
import java.text.DecimalFormat


class RecordatoriosRVAdapter(
    context: Context, eventosList: ArrayList<evento>
) : RecyclerView.Adapter<RecordatoriosRVAdapter.RecordatoriosViewHolder>() {

    private var eventosList = emptyList<evento>()
    private val context: Context

    init {
        this.eventosList = eventosList
        this.context = context
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecordatoriosViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.item_recordatorios, parent, false)
        return RecordatoriosViewHolder(
            itemView,
            context
        )
    }

    override fun getItemCount(): Int = eventosList.size

    override fun onBindViewHolder(
        holder: RecordatoriosViewHolder,
        position: Int
    ) {
        val evento: evento = eventosList[position]
        holder.bindCursoins(evento)
    }

    class RecordatoriosViewHolder(
        itemView: View, context: Context
    ) : RecyclerView.ViewHolder(itemView) {
        private var context:Context
        init {
            this.context = context
        }
        fun bindCursoins(evento: evento) {
            itemView.tv_titulo.text = evento.nombre
            itemView.tv_fechahora.text = evento.fecha + " " + evento.hora
            itemView.tv_curso.text = evento.curso
        }
    }
}