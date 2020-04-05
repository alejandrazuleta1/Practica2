package com.alejandrazuleta.clase2.ui.Calculadora.PromedioSemestre

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.setPadding
import androidx.fragment.app.Fragment
import com.alejandrazuleta.clase2.R
import com.alejandrazuleta.clase2.model.cursoinscrito
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_promedio_semestre.*
import java.math.RoundingMode
import java.text.DecimalFormat


class PromedioSemestreFragment : Fragment(), View.OnClickListener {

    val df = DecimalFormat("#.##")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_promedio_semestre, container, false)
        val buttonmas: Button = view.findViewById(R.id.bt_mas)
        val buttonmenos: Button = view.findViewById(R.id.bt_menos)
        val buttoncalcular: Button = view.findViewById(R.id.bt_calcular)
        buttonmas.setOnClickListener(this)
        buttonmenos.setOnClickListener(this)
        buttoncalcular.setOnClickListener(this)

        val myTableLayout : TableLayout = view.findViewById(R.id.tableLayout)

        val auth = FirebaseAuth.getInstance()
        val user = auth.currentUser
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("cursosinscritos")
        val myRef2 = database.getReference("cursos")

        // Read from the database
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (snapshot in dataSnapshot.children) {
                    if(snapshot.child("idusuario").child("idusuario").getValue()==user!!.uid){
                        val cursoins = snapshot.child("idusuario").getValue(cursoinscrito::class.java)

                        myRef2.addValueEventListener(object : ValueEventListener {
                            override fun onDataChange(dataSnapshot2:DataSnapshot){
                                for (postsnapshot in dataSnapshot2.children) {
                                    if(postsnapshot.key==cursoins!!.idcurso) {
                                        val row = TableRow(activity!!.applicationContext)
                                        val lp = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT)
                                        row.setLayoutParams(lp)
                                        row.setBackgroundResource(R.drawable.recttabla2)

                                        val lpi1 = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                                            TableRow.LayoutParams.MATCH_PARENT,0.65f)
                                        val lpi2 = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                                            TableRow.LayoutParams.MATCH_PARENT,0.2f)
                                        val lpi3 = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                                            TableRow.LayoutParams.MATCH_PARENT,0.15f)

                                        val nota = EditText(activity!!.applicationContext)
                                        val cursoname = EditText(activity!!.applicationContext)
                                        val creditos = EditText(activity!!.applicationContext)

                                        cursoname.setText(postsnapshot.child("nombre").getValue(
                                            String::class.java),TextView.BufferType.EDITABLE)
                                        creditos.setText(postsnapshot.child("creditos").getValue(
                                            Int::class.java)!!.toString(),TextView.BufferType.EDITABLE)

                                        var notaacum : Double=0.0
                                        for (i in cursoins.notas.indices){
                                            var aux = postsnapshot.child("evalyporc").child((i+1).toString()).child("porcentaje").getValue(Int::class.java)
                                            notaacum = notaacum + (cursoins.notas[i]*(aux!!)/100.0)
                                        }
                                        df.roundingMode = RoundingMode.CEILING
                                        nota.setText(df.format(notaacum).toString(),TextView.BufferType.EDITABLE)

                                        nota.textSize = 14F
                                        nota.setCursorVisible(true)
                                        nota.textAlignment = View.TEXT_ALIGNMENT_CENTER
                                        cursoname.textSize = 14F
                                        creditos.textSize = 14F
                                        creditos.textAlignment = View.TEXT_ALIGNMENT_CENTER

                                        cursoname.setPadding(10)
                                        creditos.setPadding(10)
                                        nota.setPadding(10)

                                        row.weightSum = 1f

                                        row.addView(cursoname,lpi1)
                                        row.addView(creditos,lpi2)
                                        row.addView(nota,lpi3)
                                        myTableLayout!!.addView(row, myTableLayout.childCount!!)
                                        break
                                    }
                                }
                            }
                            override fun onCancelled(error: DatabaseError){}
                        })
                    }
                }
            }
            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("Lista", "Failed to read value.", error.toException())
            }
        })

        return view
    }

    override fun onClick(v: View?) {
        when (v!!.id){
            R.id.bt_mas -> {
                val row = TableRow(activity!!.applicationContext)
                val lp = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT)
                row.setLayoutParams(lp)
                row.setBackgroundResource(R.drawable.recttabla2)

                val curso = EditText(activity!!.applicationContext)
                val creditos = EditText(activity!!.applicationContext)
                val nota = EditText(activity!!.applicationContext)

                val lpi1 = TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.MATCH_PARENT,0.9f)
                val lpi2 = TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.MATCH_PARENT,0.07f)
                val lpi3 = TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.MATCH_PARENT,0.03f)

                curso.setPadding(10)
                creditos.setPadding(10)
                nota.setPadding(10)

                row.weightSum = 1f

                nota.textSize = 14F
                nota.setCursorVisible(true)
                nota.textAlignment = View.TEXT_ALIGNMENT_CENTER
                curso.textSize = 14F
                creditos.textSize = 14F
                creditos.textAlignment = View.TEXT_ALIGNMENT_CENTER

                row.addView(curso,lpi1)
                row.addView(creditos,lpi2)
                row.addView(nota,lpi3)
                tableLayout.addView(row, tableLayout.childCount)
            }
            R.id.bt_menos->{
                if (tableLayout.childCount>1) {
                    tableLayout.removeViewAt(tableLayout.childCount - 1)
                }
            }
            R.id.bt_calcular->{
                var sumprods: Double = 0.0
                var sumcred: Double = 0.0
                for(i in 1..tableLayout.childCount-1){
                    val row : TableRow = tableLayout.getChildAt(i) as TableRow
                    val cred=row.getChildAt(1) as EditText
                    val nota=row.getChildAt(2) as EditText
                    sumprods=sumprods+(cred.text.toString().toDouble()*nota.text.toString().toDouble())
                    sumcred=sumcred+cred.text.toString().toDouble()
                }
                df.roundingMode = RoundingMode.CEILING
                tv_promedio.text = df.format(sumprods/sumcred).toString()
            }
        }
    }
}
