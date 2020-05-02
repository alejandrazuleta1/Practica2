package com.alejandrazuleta.clase2.ui.Horario

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.alejandrazuleta.clase2.R
import com.alejandrazuleta.clase2.model.curso
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragmento_horario.view.*

class HorarioFragment : Fragment() {
    var lista = ArrayList<String>()
    var auxcolor:Int=0
    var adapter : GVAdapter? =null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragmento_horario, container, false)

        rellenarlista()

        adapter = GVAdapter(activity?.applicationContext!!,lista)

        view.gridview.adapter = adapter

        return view
    }

    private fun rellenarlista() {
        lista.add("6 am")
        lista.add("")
        lista.add("")
        lista.add("")
        lista.add("")
        lista.add("")
        lista.add("")
        lista.add("7 am")
        lista.add("")
        lista.add("")
        lista.add("")
        lista.add("")
        lista.add("")
        lista.add("")
        lista.add("8 am")
        lista.add("")
        lista.add("")
        lista.add("")
        lista.add("")
        lista.add("")
        lista.add("")
        lista.add("9 am")
        lista.add("")
        lista.add("")
        lista.add("")
        lista.add("")
        lista.add("")
        lista.add("")
        lista.add("10 am")
        lista.add("")
        lista.add("")
        lista.add("")
        lista.add("")
        lista.add("")
        lista.add("")
        lista.add("11 am")
        lista.add("")
        lista.add("")
        lista.add("")
        lista.add("")
        lista.add("")
        lista.add("")
        lista.add("12 m")
        lista.add("")
        lista.add("")
        lista.add("")
        lista.add("")
        lista.add("")
        lista.add("")
        lista.add("1 pm")
        lista.add("")
        lista.add("")
        lista.add("")
        lista.add("")
        lista.add("")
        lista.add("")
        lista.add("2 pm")
        lista.add("")
        lista.add("")
        lista.add("")
        lista.add("")
        lista.add("")
        lista.add("")
        lista.add("3 pm")
        lista.add("")
        lista.add("")
        lista.add("")
        lista.add("")
        lista.add("")
        lista.add("")
        lista.add("4 pm")
        lista.add("")
        lista.add("")
        lista.add("")
        lista.add("")
        lista.add("")
        lista.add("")
        lista.add("5 pm")
        lista.add("")
        lista.add("")
        lista.add("")
        lista.add("")
        lista.add("")
        lista.add("")
        lista.add("6 pm")
        lista.add("")
        lista.add("")
        lista.add("")
        lista.add("")
        lista.add("")
        lista.add("")
        lista.add("7 pm")
        lista.add("")
        lista.add("")
        lista.add("")
        lista.add("")
        lista.add("")
        lista.add("")
        lista.add("8 pm")
        lista.add("")
        lista.add("")
        lista.add("")
        lista.add("")
        lista.add("")
        lista.add("")
        lista.add("9 pm")
        lista.add("")
        lista.add("")
        lista.add("")
        lista.add("")
        lista.add("")
        lista.add("")
        lista.add("10 pm")
        lista.add("")
        lista.add("")
        lista.add("")
        lista.add("")
        lista.add("")
        lista.add("")
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
                        auxcolor=auxcolor+1
                        myRef2.addValueEventListener(object : ValueEventListener {
                            override fun onDataChange(dataSnapshot2: DataSnapshot){
                                for (postsnapshot in dataSnapshot2.children) {
                                    if(postsnapshot.key==cursoins!!.idcurso) {
                                        val curso = postsnapshot.getValue(curso::class.java)

                                        for (i in curso!!.sesionesporsem!!.iterator()) {
                                            myRef3.addValueEventListener(object :
                                                ValueEventListener {
                                                override fun onDataChange(dataSnapshot3: DataSnapshot) {
                                                    for (postsnaphot2 in dataSnapshot3.children) {
                                                        if (postsnaphot2.key == i) {
                                                            val horai: Int? =
                                                                postsnaphot2.child("hora")
                                                                    .child("inicio")
                                                                    .getValue(Int::class.java)
                                                            val horaf: Int? =
                                                                postsnaphot2.child("hora")
                                                                    .child("final")
                                                                    .getValue(Int::class.java)
                                                            val nombre: String? =
                                                                postsnapshot.child("nombre")
                                                                    .getValue(String()::class.java)
                                                            val aula: String? =
                                                                postsnaphot2.child("aula")
                                                                    .getValue(String()::class.java)
                                                            val dia: Int? =
                                                                postsnaphot2.child("dia")
                                                                    .getValue(Int::class.java)
                                                            mostrar(nombre, aula, dia, horai, horaf,auxcolor)
                                                            Log.d("Info",nombre+aula+dia.toString()+horai.toString()+horaf.toString())
                                                        }
                                                    }
                                                }

                                                override fun onCancelled(error: DatabaseError) {}
                                            })
                                        }
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

    private fun mostrar(nombre: String?, aula: String?, dia: Int?, horai: Int?, horaf: Int?,auxcolor:Int) {
        val posi : Int = (horai!!-6)*7
        val posj : Int = posi+1+dia!!
        lista[posj] = nombre!! + " " + aula
        adapter!!.notifyDataSetChanged()
    }
}