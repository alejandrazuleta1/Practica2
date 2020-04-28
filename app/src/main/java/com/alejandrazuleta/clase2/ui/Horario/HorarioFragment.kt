package com.alejandrazuleta.clase2.ui.Horario

import android.annotation.SuppressLint
import android.graphics.RectF
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.setPadding
import com.alamkanak.weekview.MonthLoader
import com.alamkanak.weekview.WeekView
import com.alamkanak.weekview.WeekViewEvent
import com.alejandrazuleta.clase2.R
import com.alejandrazuleta.clase2.R.color.clase1
import com.alejandrazuleta.clase2.model.curso
import com.alejandrazuleta.clase2.model.cursoinscrito
import com.alejandrazuleta.clase2.model.sesion
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_horario.*
import java.math.RoundingMode
import java.util.*
import kotlin.collections.ArrayList


class HorarioFragment : Fragment(){

    var sesiones: MutableList<sesion?> = ArrayList()
    var cursoinscrito : cursoinscrito = cursoinscrito()
    var auxcolor:Int=0

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
                        auxcolor=auxcolor+1
                        myRef2.addValueEventListener(object : ValueEventListener {
                            override fun onDataChange(dataSnapshot2:DataSnapshot){
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
        val aux = nombre+" "+aula
        val auxhora=horai!!.minus(6)

        when(dia){
            0-> when(auxhora){
                0-> bt_00.text = aux
                1-> bt_10.text = aux
                2-> bt_20.text = aux
                3-> bt_30.text = aux
                4-> bt_40.text = aux
                5-> bt_50.text = aux
                6-> bt_60.text = aux
                7-> bt_70.text = aux
                8-> bt_80.text = aux
                9-> bt_90.text = aux
                10-> bt_100.text = aux
                11-> bt_110.text = aux
                12-> bt_120.text = aux
                13-> bt_130.text = aux
                14-> bt_140.text = aux
                15-> bt_150.text = aux
                16-> bt_160.text = aux
            }
            1-> when(auxhora){
                0-> bt_01.text = aux
                1-> bt_11.text = aux
                2-> bt_21.text = aux
                3-> bt_31.text = aux
                4-> bt_41.text = aux
                5-> bt_51.text = aux
                6-> bt_61.text = aux
                7-> bt_71.text = aux
                8-> bt_81.text = aux
                9-> bt_91.text = aux
                10-> bt_111.text = aux
                11-> bt_111.text = aux
                12-> bt_121.text = aux
                13-> bt_131.text = aux
                14-> bt_141.text = aux
                15-> bt_151.text = aux
                16-> bt_161.text = aux
            }
            2-> when(auxhora){
                0-> bt_02.text = aux
                1-> bt_12.text = aux
                2-> bt_22.text = aux
                3-> bt_32.text = aux
                4-> bt_42.text = aux
                5-> bt_52.text = aux
                6-> bt_62.text = aux
                7-> bt_72.text = aux
                8-> bt_82.text = aux
                9-> bt_92.text = aux
                10-> bt_102.text = aux
                11-> bt_112.text = aux
                12-> bt_122.text = aux
                13-> bt_132.text = aux
                14-> bt_142.text = aux
                15-> bt_152.text = aux
                16-> bt_162.text = aux
            }
            3-> when(auxhora){
                0-> bt_03.text = aux
                1-> bt_13.text = aux
                2-> bt_23.text = aux
                3-> bt_33.text = aux
                4-> bt_43.text = aux
                5-> bt_53.text = aux
                6-> bt_63.text = aux
                7-> bt_73.text = aux
                8-> bt_83.text = aux
                9-> bt_93.text = aux
                10-> bt_103.text = aux
                11-> bt_113.text = aux
                12-> bt_123.text = aux
                13-> bt_133.text = aux
                14-> bt_143.text = aux
                15-> bt_153.text = aux
                16-> bt_163.text = aux
            }
            4-> when(auxhora){
                0-> bt_04.text = aux
                1-> bt_14.text = aux
                2-> bt_24.text = aux
                3-> bt_34.text = aux
                4-> bt_44.text = aux
                5-> bt_54.text = aux
                6-> bt_64.text = aux
                7-> bt_74.text = aux
                8-> bt_84.text = aux
                9-> bt_94.text = aux
                10-> bt_104.text = aux
                11-> bt_114.text = aux
                12-> bt_124.text = aux
                13-> bt_134.text = aux
                14-> bt_144.text = aux
                15-> bt_154.text = aux
                16-> bt_164.text = aux
            }
            5-> when(auxhora){
                0-> bt_05.text = aux
                1-> bt_15.text = aux
                2-> bt_25.text = aux
                3-> bt_35.text = aux
                4-> bt_45.text = aux
                5-> bt_55.text = aux
                6-> bt_65.text = aux
                7-> bt_75.text = aux
                8-> bt_85.text = aux
                9-> bt_95.text = aux
                10-> bt_105.text = aux
                11-> bt_115.text = aux
                12-> bt_125.text = aux
                13-> bt_135.text = aux
                14-> bt_145.text = aux
                15-> bt_155.text = aux
                16-> bt_165.text = aux
            }
        }


    }
}

