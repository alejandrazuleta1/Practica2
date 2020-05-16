package com.alejandrazuleta.clase2.ui.Eventos.recordatorios

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alejandrazuleta.clase2.R
import com.alejandrazuleta.clase2.model.evento
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.item_recordatorios.view.*


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

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

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
            itemView.tv_curso.text = evento.nombrecurso
            if (evento.notificacion) itemView.checkbox1.isChecked = true
            else  itemView.checkbox1.isChecked = false

            itemView.setOnClickListener {
                val intent = Intent(context, DetalleEventoActivity::class.java)
                intent.putExtra("evento", evento).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(intent)
            }

            itemView.checkbox1.setOnClickListener {
                val database = FirebaseDatabase.getInstance()
                val myRef = database.getReference("eventos")
                val childUpdate = HashMap<String, Any>()
                childUpdate["notificacion"] = !evento.notificacion
                myRef.child(evento.id).updateChildren(childUpdate)
            }


        }
    }


}