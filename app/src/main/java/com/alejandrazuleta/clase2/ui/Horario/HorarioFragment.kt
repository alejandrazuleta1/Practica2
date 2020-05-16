package com.alejandrazuleta.clase2.ui.Horario

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import com.alejandrazuleta.clase2.R
import com.alejandrazuleta.clase2.model.curso
import com.alejandrazuleta.clase2.model.sesion
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragmento_horario.view.*

class HorarioFragment : Fragment() {
    var lista2 = ArrayList<HashMap<String,Int>>()
    var cursolist = ArrayList<curso>()
    var sesionlist = ArrayList<sesion>()
    var auxcolor:Int=1
    var adapter : GVAdapter? =null
    var idcursoanterior:String ="0"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragmento_horario, container, false)

        rellenarlista2()

        adapter = GVAdapter(activity?.applicationContext!!,lista2,cursolist,sesionlist)

        view.gridview.adapter = adapter

        return view
    }

    private fun rellenarlista2() {
        lista2.add(hashMapOf("6 am" to 0))
        lista2.add(hashMapOf("" to 1))
        lista2.add(hashMapOf("" to 1))
        lista2.add(hashMapOf("" to 1))
        lista2.add(hashMapOf("" to 1))
        lista2.add(hashMapOf("" to 1))
        lista2.add(hashMapOf("" to 1))
        lista2.add(hashMapOf("7 am" to 0))
        lista2.add(hashMapOf("" to 1))
        lista2.add(hashMapOf("" to 1))
        lista2.add(hashMapOf("" to 1))
        lista2.add(hashMapOf("" to 1))
        lista2.add(hashMapOf("" to 1))
        lista2.add(hashMapOf("" to 1))
        lista2.add(hashMapOf("8 am" to 0))
        lista2.add(hashMapOf("" to 1))
        lista2.add(hashMapOf("" to 1))
        lista2.add(hashMapOf("" to 1))
        lista2.add(hashMapOf("" to 1))
        lista2.add(hashMapOf("" to 1))
        lista2.add(hashMapOf("" to 1))
        lista2.add(hashMapOf("9 am" to 0))
        lista2.add(hashMapOf("" to 1))
        lista2.add(hashMapOf("" to 1))
        lista2.add(hashMapOf("" to 1))
        lista2.add(hashMapOf("" to 1))
        lista2.add(hashMapOf("" to 1))
        lista2.add(hashMapOf("" to 1))
        lista2.add(hashMapOf("10 am" to 0))
        lista2.add(hashMapOf("" to 1))
        lista2.add(hashMapOf("" to 1))
        lista2.add(hashMapOf("" to 1))
        lista2.add(hashMapOf("" to 1))
        lista2.add(hashMapOf("" to 1))
        lista2.add(hashMapOf("" to 1))
        lista2.add(hashMapOf("11 am" to 0))
        lista2.add(hashMapOf("" to 1))
        lista2.add(hashMapOf("" to 1))
        lista2.add(hashMapOf("" to 1))
        lista2.add(hashMapOf("" to 1))
        lista2.add(hashMapOf("" to 1))
        lista2.add(hashMapOf("" to 1))
        lista2.add(hashMapOf("12 m" to 0))
        lista2.add(hashMapOf("" to 1))
        lista2.add(hashMapOf("" to 1))
        lista2.add(hashMapOf("" to 1))
        lista2.add(hashMapOf("" to 1))
        lista2.add(hashMapOf("" to 1))
        lista2.add(hashMapOf("" to 1))
        lista2.add(hashMapOf("1 pm" to 0))
        lista2.add(hashMapOf("" to 1))
        lista2.add(hashMapOf("" to 1))
        lista2.add(hashMapOf("" to 1))
        lista2.add(hashMapOf("" to 1))
        lista2.add(hashMapOf("" to 1))
        lista2.add(hashMapOf("" to 1))
        lista2.add(hashMapOf("2 pm" to 0))
        lista2.add(hashMapOf("" to 1))
        lista2.add(hashMapOf("" to 1))
        lista2.add(hashMapOf("" to 1))
        lista2.add(hashMapOf("" to 1))
        lista2.add(hashMapOf("" to 1))
        lista2.add(hashMapOf("" to 1))
        lista2.add(hashMapOf("3 pm" to 0))
        lista2.add(hashMapOf("" to 1))
        lista2.add(hashMapOf("" to 1))
        lista2.add(hashMapOf("" to 1))
        lista2.add(hashMapOf("" to 1))
        lista2.add(hashMapOf("" to 1))
        lista2.add(hashMapOf("" to 1))
        lista2.add(hashMapOf("4 pm" to 0))
        lista2.add(hashMapOf("" to 1))
        lista2.add(hashMapOf("" to 1))
        lista2.add(hashMapOf("" to 1))
        lista2.add(hashMapOf("" to 1))
        lista2.add(hashMapOf("" to 1))
        lista2.add(hashMapOf("" to 1))
        lista2.add(hashMapOf("5 pm" to 0))
        lista2.add(hashMapOf("" to 1))
        lista2.add(hashMapOf("" to 1))
        lista2.add(hashMapOf("" to 1))
        lista2.add(hashMapOf("" to 1))
        lista2.add(hashMapOf("" to 1))
        lista2.add(hashMapOf("" to 1))
        lista2.add(hashMapOf("6 pm" to 0))
        lista2.add(hashMapOf("" to 1))
        lista2.add(hashMapOf("" to 1))
        lista2.add(hashMapOf("" to 1))
        lista2.add(hashMapOf("" to 1))
        lista2.add(hashMapOf("" to 1))
        lista2.add(hashMapOf("" to 1))
        lista2.add(hashMapOf("7 pm" to 0))
        lista2.add(hashMapOf("" to 1))
        lista2.add(hashMapOf("" to 1))
        lista2.add(hashMapOf("" to 1))
        lista2.add(hashMapOf("" to 1))
        lista2.add(hashMapOf("" to 1))
        lista2.add(hashMapOf("" to 1))
        lista2.add(hashMapOf("8 pm" to 0))
        lista2.add(hashMapOf("" to 1))
        lista2.add(hashMapOf("" to 1))
        lista2.add(hashMapOf("" to 1))
        lista2.add(hashMapOf("" to 1))
        lista2.add(hashMapOf("" to 1))
        lista2.add(hashMapOf("" to 1))
        lista2.add(hashMapOf("9 pm" to 0))
        lista2.add(hashMapOf("" to 1))
        lista2.add(hashMapOf("" to 1))
        lista2.add(hashMapOf("" to 1))
        lista2.add(hashMapOf("" to 1))
        lista2.add(hashMapOf("" to 1))
        lista2.add(hashMapOf("" to 1))
        lista2.add(hashMapOf("10 pm" to 0))
        lista2.add(hashMapOf("" to 1))
        lista2.add(hashMapOf("" to 1))
        lista2.add(hashMapOf("" to 1))
        lista2.add(hashMapOf("" to 1))
        lista2.add(hashMapOf("" to 1))
        lista2.add(hashMapOf("" to 1))

        val auxsesion:sesion = sesion()
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)
        sesionlist.add(auxsesion)

        val auxcurso:curso = curso()
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
        cursolist.add(auxcurso)
    }

    override fun onResume() {
        super.onResume()
        cargarCursosIns()
    }

    private fun cargarCursosIns() {
        auxcolor=1
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
                                                            val sesion = postsnaphot2.getValue(sesion::class.java)
                                                            mostrar(curso,sesion!!)
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

    private fun mostrar(curso: curso, sesion: sesion) {
        if(!curso.id.equals(idcursoanterior)){
            auxcolor = auxcolor+1
        }
        val nombre = curso.nombre
        val horai=sesion.hora["inicio"]
        val horaf =sesion.hora["final"]
        val duracion = horaf!!-horai!!
        val dia = sesion.dia
        val aula = sesion.aula

        val posi : Int = (horai!!-6)*7
        val posj : Int = posi+1+dia
        val aux = nombre + " " + aula

        lista2[posj] = hashMapOf(aux to auxcolor)
        sesionlist.add(posj,sesion!!)
        cursolist.add(posj,curso!!)
        for (i in 1..duracion-1){
            lista2[posj+7*i]= hashMapOf(" " to auxcolor)
            Log.d("color",posj.toString() + lista2[posj+7*i].keys.elementAt(0) + lista2[posj+7*i].values.elementAt(0).toString())
        }
        adapter!!.notifyDataSetChanged()
        idcursoanterior = curso.id
    }
}