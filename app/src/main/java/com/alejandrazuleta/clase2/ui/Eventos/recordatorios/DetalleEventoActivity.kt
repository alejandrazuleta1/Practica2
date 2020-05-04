package com.alejandrazuleta.clase2.ui.Eventos.recordatorios

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import com.alejandrazuleta.clase2.R
import com.alejandrazuleta.clase2.model.evento
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_detalle_evento.*

class DetalleEventoActivity : AppCompatActivity() {

    var evento : evento = evento()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_evento)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setTitle("Detalles recordatorio")

        evento = intent?.getSerializableExtra("evento") as evento
        tv_titulo.text = evento.nombre
        tv_curso.text = evento.nombrecurso
        tv_fechahora.text = evento.fecha + " " + evento.hora
        tv_descripcion.text = evento.descripcion

        bt_eliminar.setOnClickListener {
            val database = FirebaseDatabase.getInstance()
            val myRef = database.getReference("eventos")

            val alertDialog: AlertDialog? = this.let{
                val builder = AlertDialog.Builder(it)
                builder.apply {
                    setMessage("Estás seguro?")
                    setPositiveButton(
                        "Eliminar"
                    ) { dialog, id ->
                        myRef.child(evento.id).removeValue()
                        onBackPressed()
                    }
                    setNegativeButton(
                        "Cancelar"
                    ) {dialog, id ->
                    }
                }
                builder.create()
            }
            alertDialog?.show()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

}
