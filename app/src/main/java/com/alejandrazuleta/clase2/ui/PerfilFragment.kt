package com.alejandrazuleta.clase2.ui

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import com.alejandrazuleta.clase2.LoginActivity
import com.alejandrazuleta.clase2.R
import com.alejandrazuleta.clase2.model.facultad
import com.alejandrazuleta.clase2.model.usuario
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.app_bar_main.view.*
import kotlinx.android.synthetic.main.fragment_perfil.*


class PerfilFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val auth = FirebaseAuth.getInstance()
        val user = auth.currentUser
        if (user != null) {
            // already signed in
            val database = FirebaseDatabase.getInstance()
            val myRef = database.getReference("usuarios")
            val myRef2 = database.getReference("facultades")
            // Read from the database

            var miUsuario : usuario?
            var miFacultad : facultad?

            myRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (postSnapshot in dataSnapshot.children) {
                        if(postSnapshot.key==user.uid){
                            miUsuario= postSnapshot.getValue(usuario::class.java)
                            tv_nombre.text = miUsuario!!.nombre
                            //tv_facultad.text = user.idfacultad
                            tv_programa.text = miUsuario!!.programa
                            tv_numprom.text = miUsuario!!.promedio.toString()
                            val aux = (miUsuario!!.avance*100/miUsuario!!.total).toString()+"%"
                            tv_numavance.text = aux
                            AvanceBar.progress = miUsuario!!.avance*100/miUsuario!!.total

                            myRef2.addValueEventListener(object : ValueEventListener {
                                override fun onDataChange(dataSnapshot2: DataSnapshot) {
                                    for (postSnapshot2 in dataSnapshot2.children) {
                                        if(postSnapshot2.key==miUsuario!!.idfacultad){
                                            miFacultad= postSnapshot2.getValue(facultad::class.java)
                                            val aux2= "Facultad de " + miFacultad!!.nombre
                                            tv_facultad.text = aux2
                                            break
                                        }
                                    }
                                }
                                override fun onCancelled(error: DatabaseError) {
                                    // Failed to read value
                                    Log.w("ReadFacultad", "Failed to read value.", error.toException())
                                }
                            })
                            break
                        }
                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    // Failed to read value
                    Log.w("ReadV", "Failed to read value.", error.toException())
                }
            })
        } else {
            // No user is signed in.
            //mostrar datos guardados de ROOM si hay
        }
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_perfil, container, false)

        setHasOptionsMenu(true);

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.m_perfil, menu);
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.nav_cerrarsesion) {
            val alertDialog: AlertDialog? = activity.let {
                val builder = AlertDialog.Builder(it)
                builder.apply {
                    setMessage("Estás seguro que deseas cerrar sesión?")
                    setPositiveButton(
                        "Sí"
                    ) { dialog, id ->
                        //ciero sesion en firebase
                        val auth = FirebaseAuth.getInstance()
                        auth.signOut()
                        goToLoginActivity()
                    }
                    setNegativeButton(
                        "No"
                    ) { dialog, id ->
                    }
                }
                builder.create()
            }
            alertDialog?.show()
        }
        return super.onOptionsItemSelected(item)
    }

    fun goToLoginActivity() {
        var intent = Intent(activity, LoginActivity::class.java)
        startActivity(intent)
        activity?.finish()
    }

}
