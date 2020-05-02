package com.alejandrazuleta.clase2.ui.Horario

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.alejandrazuleta.clase2.R
import com.alejandrazuleta.clase2.model.curso
import com.alejandrazuleta.clase2.model.sesion
import kotlinx.android.synthetic.main.activity_horario_detalle.*

class HorarioDetalleActivity : AppCompatActivity() {

    var curso: curso = curso()
    var sesion: sesion = sesion()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_horario_detalle)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        curso = intent?.getSerializableExtra("curso") as curso
        sesion = intent?.getSerializableExtra("sesion") as sesion
        supportActionBar!!.title = curso.nombre
        tv_aula.text = sesion.aula
        tv_profesor.text = curso.profesor
        tv_creditos.text = curso.creditos.toString()


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

}
