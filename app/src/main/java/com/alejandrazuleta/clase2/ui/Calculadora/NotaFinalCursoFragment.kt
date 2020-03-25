package com.alejandrazuleta.clase2.ui.Calculadora.NotaFinal


import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.setPadding
import androidx.fragment.app.Fragment
import com.alejandrazuleta.clase2.R
import com.alejandrazuleta.clase2.model.curso
import com.alejandrazuleta.clase2.model.cursoinscrito
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_nota_final_curso.*
import java.math.RoundingMode
import java.text.DecimalFormat


class NotaFinalCursoFragment : Fragment(), AdapterView.OnItemSelectedListener,
    View.OnClickListener {

    val allCursosInscritos: MutableList<cursoinscrito> = mutableListOf()
    val allCursos: MutableList<curso> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_nota_final_curso, container, false)

        val buttoncalcular: Button = view.findViewById(R.id.bt_calcular)
        buttoncalcular.setOnClickListener(this)

        val auth = FirebaseAuth.getInstance()
        val user = auth.currentUser

        val cursosinscritos:MutableList<String> = mutableListOf()

        val sp_cursos2: Spinner = view.findViewById(R.id.sp_cursos)

        val adapter2 = ArrayAdapter(
            activity!!.applicationContext,
            //android.R.layout.simple_spinner_item,
            R.layout.spinner_item_text,
            cursosinscritos
        )
        sp_cursos2.adapter = adapter2
        sp_cursos2.onItemSelectedListener=this@NotaFinalCursoFragment

        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("cursosinscritos")
        val myRef2 = database.getReference("cursos")

        // Read from the database
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (snapshot in dataSnapshot.children) {
                    if(snapshot.child("idusuario").child("idusuario").getValue()==user!!.uid){
                        val cursoins = snapshot.child("idusuario").getValue(cursoinscrito::class.java)
                        allCursosInscritos.add(cursoins!!)

                        myRef2.addValueEventListener(object : ValueEventListener {
                            override fun onDataChange(dataSnapshot: DataSnapshot) {
                                for (postSnapshot in dataSnapshot.children) {
                                    if(postSnapshot.key==cursoins!!.idcurso){
                                        cursosinscritos.add(postSnapshot.child("nombre").getValue().toString())
                                        val curso = postSnapshot.getValue(curso::class.java)
                                        allCursos.add(curso!!)
                                        break
                                    }
                                }
                                adapter2.notifyDataSetChanged()
                            }
                            override fun onCancelled(error: DatabaseError) {
                                // Failed to read value
                                Log.w("ReadCurso", "Failed to read value.", error.toException())
                            }
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

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        if (tableLayout.childCount > 1) {
            tableLayout.removeViews(1, tableLayout.childCount - 1)
        }
        for (i in allCursosInscritos[position].notas.indices) {
            val row = TableRow(activity!!.applicationContext)
            val lp = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT)
            row.setLayoutParams(lp)
            row.setBackgroundResource(R.drawable.recttabla)

            val porc = TextView(activity!!.applicationContext)
            val descripcion = TextView(activity!!.applicationContext)
            val nota = TextView(activity!!.applicationContext)

            porc.setText(allCursos[position].evalyporc[i+1].porcentaje.toString())
            descripcion.setText(allCursos[position].evalyporc[i+1].nombre)
            nota.setText(allCursosInscritos[position].notas[i].toString())

            val lpi1 = TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.MATCH_PARENT,0.9f)
            val lpi2 = TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.MATCH_PARENT,0.05f)
            val lpi3 = TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.MATCH_PARENT,0.05f)

            porc.setPadding(10)
            descripcion.setPadding(10)
            nota.setPadding(10)

            row.weightSum = 1f

            row.addView(descripcion,lpi1)
            row.addView(porc,lpi2)
            row.addView(nota,lpi3)
            tableLayout.addView(row, i + 1);
        }
    }
    override fun onNothingSelected(parent: AdapterView<*>?) {
    }

    override fun onClick(v: View?) {
        when (v!!.id){
            R.id.bt_calcular ->{
                var sumprods: Double = 0.0
                var sumporc: Int = 0
                for(i in 1..tableLayout.childCount-1){
                    val row : TableRow = tableLayout.getChildAt(i) as TableRow
                    val porc=row.getChildAt(1) as TextView
                    val nota=row.getChildAt(2) as TextView
                    sumprods=sumprods+(porc.text.toString().toInt()*nota.text.toString().toDouble()/100)
                    sumporc=sumporc+porc.text.toString().toInt()
                }
                val df = DecimalFormat("#.##")
                df.roundingMode = RoundingMode.CEILING
                if (sumporc==100){
                    tv_nota.text = df.format(sumprods).toString()
                }else{
                    //tv_nota.text = df.format((3.0-sumprods)*100/(100.0-sumporc)).toString()
                    val alertDialog: AlertDialog? = activity.let {
                        val builder = AlertDialog.Builder(it)
                        builder.apply {
                            val aux = "Para obtener 3.0 te hace falta: " +
                                    df.format((3.0-sumprods)*100/(100.0-sumporc)).toString() + " en el " +
                                    (100-sumporc).toString() + "%"
                            setMessage(aux)
                            setNegativeButton(
                                "Ok"
                            ) { dialog, id ->
                            }
                        }
                        builder.create()
                    }
                    alertDialog?.show()
                }

            }
        }
    }

}
