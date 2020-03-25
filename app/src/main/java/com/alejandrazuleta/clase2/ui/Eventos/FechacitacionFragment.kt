package com.alejandrazuleta.clase2.ui.Eventos

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alejandrazuleta.clase2.Practica2
import com.alejandrazuleta.clase2.R
import com.alejandrazuleta.clase2.model.Usuario
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_fechacitacion.*

class FechacitacionFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =inflater.inflate(R.layout.fragment_fechacitacion, container, false)

        return view
    }

    override fun onStart() {
        super.onStart()
        var miUsuario : Usuario?
        val usuarioDAO = Practica2.database.UsuarioDAO()
        if (usuarioDAO.loadAllUsers().size>0) {
            //poner datos guardados en room
            miUsuario = usuarioDAO.loadAllUsers()[0]
            //mostrar datos guardados de ROOM si hay
            tv_fecha.text = miUsuario.citacion
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
                            tv_fecha.text = miUsuario!!.citacion
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

    }


}
