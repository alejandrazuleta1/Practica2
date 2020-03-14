package com.alejandrazuleta.clase2

import android.os.Bundle
import android.view.MenuItem
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.alejandrazuleta.clase2.model.cursoinscrito
import com.alejandrazuleta.clase2.model.evaluacion
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.content_nota_detalle.*
import kotlinx.android.synthetic.main.nav_header_main.*


class NotaDellateActivity : AppCompatActivity() {
    val porcentajes: List<Int> = arrayListOf<Int>()
    var evaluaciones: MutableList<evaluacion?> = ArrayList()
    var cursoinscrito : cursoinscrito = cursoinscrito()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = R.layout.activity_nota_dellate
        setContentView(view)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        cursoinscrito = intent?.getSerializableExtra("cursoinscrito") as cursoinscrito
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

            val porc = TextView(this)
            val descripcion = TextView(this)
            val nota = TextView(this)

            porc.setText(evaluaciones[i]!!.porcentaje.toString())
            descripcion.setText(evaluaciones[i]!!.nombre.toString())
            nota.setText(cursoinscrito.notas[i].toString())

            /*val params = porc.getLayoutParams() as TableRow.LayoutParams
            params.weight = 0.2f
            porc.setLayoutParams(params)
            nota.setLayoutParams(params)
            params.weight = 0.6f
            descripcion.setLayoutParams(params)*/

            row.addView(porc)
            row.addView(descripcion)
            row.addView(nota)
            tableLayout.addView(row, i + 1);
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
