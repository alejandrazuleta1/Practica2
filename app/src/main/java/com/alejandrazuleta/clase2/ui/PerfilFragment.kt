package com.alejandrazuleta.clase2.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.alejandrazuleta.clase2.R
import com.alejandrazuleta.clase2.model.usuario
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_perfil.*

class PerfilFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_perfil, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val auth = FirebaseAuth.getInstance()
        val user = auth.currentUser
        if (user != null) {
            // already signed in
            val database = FirebaseDatabase.getInstance()
            /*val myRef = database.getReference("usuarios")
            // Read from the database
            myRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val usuario = dataSnapshot.getValue(usuario::class.java)
                    Log.d("Read Value", "Value is: $usuario")
                    if (usuario!!.id.equals(user.uid)) {
                        tv_nombre.text = usuario.nombre
                        //tv_facultad.text = user.idfacultad
                        tv_programa.text = usuario.programa
                        tv_numprom.text = usuario.promedio.toString()
                        val aux = (usuario.avance*100/usuario.total).toString()+R.string.porc
                        tv_numavance.text = aux
                        AvanceBar.progress = usuario.avance*100/usuario.total
                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    // Failed to read value
                    Log.w("Read Value", "Failed to read value.", error.toException())
                }
            })*/
        } else {
            // No user is signed in.
            //mostrar datos guardados de ROOM si hay
        }
    }
}
