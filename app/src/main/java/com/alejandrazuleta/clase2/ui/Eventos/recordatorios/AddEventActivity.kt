package com.alejandrazuleta.clase2.ui.Eventos.recordatorios

import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.alejandrazuleta.clase2.R
import com.alejandrazuleta.clase2.model.curso
import com.alejandrazuleta.clase2.model.cursoinscrito
import com.alejandrazuleta.clase2.model.evento
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_add_event.*
import kotlinx.android.synthetic.main.content_add_event.*
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class AddEventActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener{

    val allCursosInscritos: MutableList<cursoinscrito> = mutableListOf()
    val allCursos: MutableList<curso> = mutableListOf()
    private var cal= Calendar.getInstance()
    private lateinit var fecha : String
    private lateinit var hora : String
    private var mcurso : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_event)
        setSupportActionBar(toolbar)
        supportActionBar!!.title = "Nuevo recodatorio"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        var sp_cursos2: Spinner = this.sp_cursos
        sp_cursos2.onItemSelectedListener = this

        val currentDateTime = LocalDateTime.now()
        tv_fecha.text = currentDateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
        tv_hora.text = currentDateTime.format(DateTimeFormatter.ofPattern("HH:mm"))

        val dataSetListener =object: DatePickerDialog.OnDateSetListener{
            override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                cal.set(Calendar.YEAR,year)
                cal.set(Calendar.MONTH,month)
                cal.set(Calendar.DAY_OF_MONTH,dayOfMonth)

                val format = "dd/MM/yyyy"
                val sdf = SimpleDateFormat(format,Locale.US)

                fecha =sdf.format(cal.time).toString()
                Log.d("id",view!!.id.toString())
                tv_fecha.text = fecha
                Log.d("fecha: ",fecha)
            }
        }

        val dataSetListener3 = object : TimePickerDialog.OnTimeSetListener{
            override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
                cal.set(Calendar.HOUR,hourOfDay)
                cal.set(Calendar.MINUTE,minute)

                val format = "HH:mm"
                val sdf = SimpleDateFormat(format,Locale.US)
                hora =sdf.format(cal.time).toString()
                Log.d("id",view!!.id.toString())
                tv_hora.text = hora
                Log.d("hora: ",hora)
            }

        }

        tv_fecha.setOnClickListener {
            DatePickerDialog(this,
                dataSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        tv_hora.setOnClickListener {
            TimePickerDialog(this,
                dataSetListener3,
                cal.get(Calendar.HOUR_OF_DAY),
                cal.get(Calendar.MINUTE),
                true).show()
        }

        fab.setOnClickListener {
            if(tied_titulo.text!!.isNotEmpty()){
                guardarevento()
                val intent = Intent()
                setResult(Activity.RESULT_OK,intent)
                finish()
            }else{
                Toast.makeText(this,"Debe escribir un título",Toast.LENGTH_SHORT).show()
            }
        }

        val auth = FirebaseAuth.getInstance()
        val user = auth.currentUser

        val cursosinscritos:MutableList<String> = mutableListOf()
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("cursosinscritos")
        val myRef2 = database.getReference("cursos")

        val adapter2 = ArrayAdapter(
            this,
            R.layout.support_simple_spinner_dropdown_item,
            cursosinscritos
        )
        sp_cursos2 = sp_cursos
        sp_cursos2.adapter = adapter2

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

    }

    private fun guardarevento() {
        val auth = FirebaseAuth.getInstance()
        val user = auth.currentUser
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("eventos")
        val idEvento = myRef.push().key

        val evento = evento(
            idEvento!!,
            user!!.uid,
            tied_titulo.text.toString(),
            tied_descripcion.text.toString(),
            tv_fecha.text.toString(),
            tv_hora.text.toString(),
            mcurso,
            true
        )
        myRef.child(idEvento).setValue(evento)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent()
        setResult(Activity.RESULT_CANCELED,intent)
        finish()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        mcurso = parent!!.getItemAtPosition(position).toString()
    }
}


