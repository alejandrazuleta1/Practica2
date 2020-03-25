package com.alejandrazuleta.clase2.ui.Eventos


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableRow
import android.widget.TextView
import androidx.core.view.setPadding
import com.alejandrazuleta.clase2.R
import com.alejandrazuleta.clase2.model.Usuario
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_calendario.*

/**
 * A simple [Fragment] subclass.
 */
class CalendarioFragment : Fragment() {

    val auth = FirebaseAuth.getInstance()
    val user = auth.currentUser

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_calendario, container, false)

        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("usuarios")
        val myRef2 = database.getReference("facultades")

        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (snapshot in dataSnapshot.children) {
                    if (snapshot.key == user!!.uid) {
                        val myUser = snapshot.getValue(Usuario::class.java)

                        myRef2.addValueEventListener(object : ValueEventListener {
                            override fun onDataChange(p0: DataSnapshot) {
                                for (snapshot2 in p0.children) {
                                    if (snapshot2.key == myUser!!.facultad) {
                                        var aux = 0
                                        for (i in snapshot2.child("calendarioac").children) {
                                            val row : TableRow = tableLayout.getChildAt(aux) as TableRow
                                            val lpi1 = TableRow.LayoutParams(
                                                TableRow.LayoutParams.WRAP_CONTENT,
                                                TableRow.LayoutParams.MATCH_PARENT, 0.7f
                                            )

                                            val fecha = TextView(activity!!.applicationContext)
                                            fecha.setText(i.getValue(String::class.java))

                                            fecha.textSize = 14F
                                            fecha.textAlignment = View.TEXT_ALIGNMENT_CENTER
                                            fecha.setPadding(10)

                                            row.addView(fecha, lpi1)

                                            tableLayout.removeViewAt(aux)
                                            tableLayout.addView(row, aux)
                                            aux=aux+1
                                        }
                                    }
                                }
                            }

                            override fun onCancelled(p0: DatabaseError) {
                                Log.w("ListaFacultades", "Failed to read value.")
                            }
                        })
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("ListaUsuario", "Failed to read value.", error.toException())
            }
        })

        return view
    }
}
