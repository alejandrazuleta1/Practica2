package com.alejandrazuleta.clase2.ui.Notas

import android.os.Bundle
import android.view.MenuItem
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.setPadding
import com.alejandrazuleta.clase2.R
import com.alejandrazuleta.clase2.model.cursoinscrito
import com.alejandrazuleta.clase2.model.evaluacion
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.content_nota_detalle.*


class NotaDellateActivity : AppCompatActivity() {
    var evaluaciones: MutableList<evaluacion?> = ArrayList()
    var cursoinscrito : cursoinscrito = cursoinscrito()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = R.layout.activity_nota_dellate
        setContentView(view)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        cursoinscrito = intent?.getSerializableExtra("cursoinscrito") as cursoinscrito
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
        cargarEvaluaciones()
    }

    private fun cargarEvaluaciones() {
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("cursos")

        evaluaciones.clear()

        myRef.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (postSnapshot in dataSnapshot.children) {
                    if (postSnapshot.key == cursoinscrito.idcurso) {
                        setTitle(postSnapshot.child("nombre").getValue().toString())
                        for (post in postSnapshot.child("evalyporc").children) {
                            evaluaciones.add(post.getValue(evaluacion::class.java))
                        }
                        cargarView()
                    }
                }
            }
        })
    }

    private fun cargarView() {
        for (i in cursoinscrito.notas.indices) {
            val row = TableRow(this)
            val lp = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT)
            row.setLayoutParams(lp)
            row.setBackgroundResource(R.drawable.recttabla)

            val porc = TextView(this)
            val descripcion = TextView(this)
            val nota = TextView(this)

            porc.setText(evaluaciones[i]!!.porcentaje.toString())
            descripcion.setText(evaluaciones[i]!!.nombre.toString())
            nota.setText(cursoinscrito.notas[i].toString())

            val lpi1 = TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.MATCH_PARENT,0.1f)
            val lpi2 = TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.MATCH_PARENT,0.9f)
            val lpi3 = TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.MATCH_PARENT,0.08f)

            porc.setPadding(10)
            descripcion.setPadding(10)
            nota.setPadding(10)

            row.weightSum = 1f

            row.addView(porc,lpi1)
            row.addView(descripcion,lpi2)
            row.addView(nota,lpi3)
            tableLayout.addView(row, i + 1);
        }
    }
}
