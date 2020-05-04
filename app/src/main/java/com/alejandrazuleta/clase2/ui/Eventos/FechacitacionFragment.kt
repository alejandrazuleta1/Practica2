package com.alejandrazuleta.clase2.ui.Eventos

import android.content.Intent
import android.os.Bundle
import android.provider.CalendarContract
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.alejandrazuleta.clase2.Practica2
import com.alejandrazuleta.clase2.R
import com.alejandrazuleta.clase2.model.Usuario
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_fechacitacion.*
import kotlinx.android.synthetic.main.fragment_fechacitacion.view.*
import kotlinx.android.synthetic.main.fragment_fechacitacion.view.tv_fecha
import kotlinx.android.synthetic.main.fragment_fechacitacion.view.tv_hora
import java.util.*


class FechacitacionFragment : Fragment(), View.OnClickListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =inflater.inflate(R.layout.fragment_fechacitacion, container, false)

        var miUsuario : Usuario?
        val usuarioDAO = Practica2.database.UsuarioDAO()
        if (usuarioDAO.loadAllUsers().size>0) {
            //poner datos guardados en room
            miUsuario = usuarioDAO.loadAllUsers()[0]
            //mostrar datos guardados de ROOM si hay
            view.tv_tanda.text = miUsuario.citacion[0]
            view.tv_fecha.text = miUsuario.citacion[1]
            view.tv_hora.text = miUsuario.citacion[2]
        }

        val auth = FirebaseAuth.getInstance()
        val user = auth.currentUser
        if (user != null) {
            // already signed in
            val database = FirebaseDatabase.getInstance()
            val myRef = database.getReference("usuarios")

            // Read from the database
            myRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (postSnapshot in dataSnapshot.children) {
                        if(postSnapshot.key==user.uid){
                            miUsuario= postSnapshot.getValue(Usuario::class.java)
                            view.tv_tanda.text = miUsuario!!.citacion[0]
                            view.tv_fecha.text = miUsuario!!.citacion[1]
                            view.tv_hora.text = miUsuario!!.citacion[2]
                            break
                        }
                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    // Failed to read value
                    Log.w("ReadV", "Failed to read value.", error.toException())
                }
            })
        }

        view.floatingActionButton2.setOnClickListener(this)
        return view
    }

    override fun onClick(v: View?) {
        val time = tv_hora.text.split(":")
        val date = tv_fecha.text.split("/")

        val startMillis: Long = Calendar.getInstance().run {
            set(date[2].toInt(), date[1].toInt(), date[0].toInt(), time[0].toInt(),time[1].toInt())
            timeInMillis
        }
        val endMillis: Long = Calendar.getInstance().run {
            set(date[2].toInt(), date[1].toInt(), date[0].toInt(), time[0].toInt()+1,time[1].toInt())
            timeInMillis
        }
        val intent = Intent(Intent.ACTION_INSERT)
            .setData(CalendarContract.Events.CONTENT_URI)
            .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, startMillis)
            .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endMillis)
            .putExtra(CalendarContract.Events.TITLE, "Inscripcion Cursos UdeA")
            .putExtra(CalendarContract.Events.EVENT_LOCATION, "Cualquier dispositivo conectado a internet")
            .putExtra(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_BUSY)
        startActivity(intent)
    }

}



