package com.alejandrazuleta.clase2.ui.Notas

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alejandrazuleta.clase2.R
import com.alejandrazuleta.clase2.model.cursoinscrito
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.item_notas_rv.view.*


class NotasRVAdapter(
    context: Context, cursosinsList: ArrayList<cursoinscrito>
) : RecyclerView.Adapter<NotasRVAdapter.NotasViewHolder>() {

    private var cursosinsList = emptyList<cursoinscrito>()
    private val context: Context

    init {
        this.cursosinsList = cursosinsList
        this.context = context
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NotasViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.item_notas_rv, parent, false)
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
        itemView: View, context: Context
    ) : RecyclerView.ViewHolder(itemView) {
        private var context:Context
        init {
            this.context = context
        }
        fun bindCursoins(cursoinscrito: cursoinscrito) {
            //consultar el nombre del curso según el id que tiene

            val database = FirebaseDatabase.getInstance()
            val myRef = database.getReference("cursos")
            var acum : Double =0.0
            var porcentajeacum : Int = 0

            myRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (postSnapshot in dataSnapshot.children) {
                        if(postSnapshot.key==cursoinscrito.idcurso){
                            itemView.tv_nombre_curso.text = postSnapshot.child("nombre").getValue().toString()
                            for(i in cursoinscrito.notas.indices){
                                var aux = postSnapshot.child("evalyporc").child((i+1).toString()).child("porcentaje").getValue(Int::class.java)
                                acum = acum + (cursoinscrito.notas[i]*(aux!!)/100.0)
                                porcentajeacum = porcentajeacum + aux
                            }
                            itemView.tv_acumulado.text = acum.toString()
                            val aux = porcentajeacum.toString() + "%"
                            itemView.tv_porc_evaluado.text = aux
                            break
                        }
                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    // Failed to read value
                    Log.w("ReadCurso", "Failed to read value.", error.toException())
                }
            })

            itemView.setOnClickListener {
                var intent = Intent(context, NotaDellateActivity::class.java)
                intent.putExtra("cursoinscrito", cursoinscrito).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(intent)
            }

        }
    }
}