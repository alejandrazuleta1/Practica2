package com.alejandrazuleta.clase2.ui.Horario

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TableRow
import android.widget.TextView
import androidx.core.view.setPadding
import com.alejandrazuleta.clase2.R
import com.alejandrazuleta.clase2.model.cursoinscrito
import com.alejandrazuleta.clase2.model.sesion
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.math.RoundingMode


class HorarioFragment : Fragment(){

    var sesiones: MutableList<sesion?> = ArrayList()
    var cursoinscrito : cursoinscrito = cursoinscrito()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_horario, container, false)
        return view
    }

    override fun onResume() {
        super.onResume()
        cargarCursosIns()
    }

    private fun cargarCursosIns() {
        val auth = FirebaseAuth.getInstance()
        val user = auth.currentUser
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("cursosinscritos")
        val myRef2 = database.getReference("cursos")
        val myRef3 = database.getReference("sesiones")

        // Read from the database
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (snapshot in dataSnapshot.children) {
                    if(snapshot.child("idusuario").child("idusuario").getValue()==user!!.uid){
                        val cursoins = snapshot.child("idusuario").getValue(com.alejandrazuleta.clase2.model.cursoinscrito::class.java)

                        myRef2.addValueEventListener(object : ValueEventListener {
                            override fun onDataChange(dataSnapshot2:DataSnapshot){
                                for (postsnapshot in dataSnapshot2.children) {
                                    if(postsnapshot.key==cursoins!!.idcurso) {
                                        val sesion = postsnapshot.child("sesionesporsem").getValue(com.alejandrazuleta.clase2.model.sesion::class.java)

                                        myRef3.addValueEventListener(object : ValueEventListener{
                                            override fun onDataChange(dataSnapshot3:DataSnapshot){
                                                for (postsnaphot2 in dataSnapshot3.children){
                                                    if(postsnaphot2.key == sesion!!.id){
                                                        val horai:Int? = postsnaphot2.child("hora").child("inicio").getValue(Int::class.java)
                                                        val horaf:Int? = postsnaphot2.child("hora").child("final").getValue(Int::class.java)
                                                        val nombre:String? = postsnapshot.child("nombre").getValue(String()::class.java)
                                                        val aula:String? = postsnaphot2.child("aula").getValue(String()::class.java)
                                                        val dia:Int? = postsnaphot2.child("dia").getValue(Int::class.java)
                                                        mostrar(nombre,aula,dia,horai,horaf)
                                                        break
                                                    }
                                                    break
                                                }
                                            }
                                            override fun onCancelled(error: DatabaseError){}
                                        })
                                    }
                                }
                            }
                            override fun onCancelled(error: DatabaseError){}
                        })
                    }
                }
            }
            override fun onCancelled(error: DatabaseError) {
                Log.w("Lista", "Failed to read value.", error.toException())
            }
        })


    }

    private fun mostrar(nombre: String?, aula: String?, dia: Int?, horai: Int?, horaf: Int?) {
        
    }

}

