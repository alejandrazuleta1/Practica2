package com.alejandrazuleta.clase2.ui

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.alejandrazuleta.clase2.LoginActivity
import com.alejandrazuleta.clase2.Practica2
import com.alejandrazuleta.clase2.R
import com.alejandrazuleta.clase2.model.Usuario
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class PerfilFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_perfil, container, false)
        val floatingActionButton : FloatingActionButton = view.findViewById(R.id.fa_cerrarsesion)

        floatingActionButton.setOnClickListener{
            val alertDialog: AlertDialog? = activity.let {
                val builder = AlertDialog.Builder(it)
                builder.apply {
                    setMessage("Estás seguro que deseas cerrar sesión?")
                    setPositiveButton(
                        "Sí"
                    ) { dialog, id ->
                        //ciero sesion en firebase y borro usuario en room
                        val auth = FirebaseAuth.getInstance()
                        val usuarioDAO = Practica2.database.UsuarioDAO()
                        val user = auth.currentUser
                        if(user!=null){
                            usuarioDAO.deleteUsuario(usuarioDAO.searchUsuario(user.uid))
                        }
                        auth.signOut()
                        signOut()
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
        //setHasOptionsMenu(true)

        val mytv_nombre :TextView = view.findViewById(R.id.tv_nombre)
        val mytv_facultad :TextView =view.findViewById(R.id.tv_facultad)
        val mytv_programa : TextView = view.findViewById(R.id.tv_programa)
        val mytv_numprom : TextView = view.findViewById(R.id.tv_numprom)
        val mytv_numavance : TextView =view.findViewById(R.id.tv_numavance)
        val myAvanceBar: ProgressBar =view.findViewById(R.id.AvanceBar)

        var miUsuario : Usuario?

        val usuarioDAO = Practica2.database.UsuarioDAO()

        if (usuarioDAO.loadAllUsers().size>0) {
            //poner datos guardados en room
            miUsuario = usuarioDAO.loadAllUsers()[0]
            //mostrar datos guardados de ROOM si hay
            mytv_nombre.text = miUsuario.nombre
            var aux = "Facultad de " + miUsuario.facultad
            mytv_facultad.text = aux
            mytv_programa.text = miUsuario.programa
            mytv_numprom.text = miUsuario.promedio.toString()
            aux = (miUsuario.avance * 100 / miUsuario.total).toString() + "%"
            mytv_numavance.text = aux
            myAvanceBar.progress = miUsuario.avance * 100 / miUsuario.total
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
                            mytv_nombre!!.text = miUsuario!!.nombre
                            var aux = "Facultad de " + miUsuario!!.facultad
                            mytv_facultad.text = aux
                            mytv_programa.text = miUsuario!!.programa
                            mytv_numprom.text = miUsuario!!.promedio.toString()
                            aux = (miUsuario!!.avance*100/miUsuario!!.total).toString()+"%"
                            mytv_numavance.text = aux
                            myAvanceBar.progress = miUsuario!!.avance*100/miUsuario!!.total

                            //guardar los datos leidos en room
                            usuarioDAO.insertUser(miUsuario!!)
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

        return view
    }

    private fun signOut() {
        val gso =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()
        val mGoogleSignInClient = GoogleSignIn.getClient(activity!!.applicationContext, gso);
        mGoogleSignInClient.signOut()
            .addOnCompleteListener(activity!!, OnCompleteListener<Void?> {
                Log.d("SignOut", "Successful")
            })
    }

    /*
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
                        //ciero sesion en firebase y borro usuario en room
                        val auth = FirebaseAuth.getInstance()
                        val usuarioDAO = Practica2.database.UsuarioDAO()
                        val user = auth.currentUser
                        if(user!=null){
                            usuarioDAO.deleteUsuario(usuarioDAO.searchUsuario(user.uid))
                        }
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
    }*/

    fun goToLoginActivity() {
        var intent = Intent(activity, LoginActivity::class.java)
        startActivity(intent)
        activity?.finish()
    }

}
