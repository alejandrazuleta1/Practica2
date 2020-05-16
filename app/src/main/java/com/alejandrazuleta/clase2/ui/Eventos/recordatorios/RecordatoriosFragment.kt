package com.alejandrazuleta.clase2.ui.Eventos.recordatorios


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alejandrazuleta.clase2.R
import com.alejandrazuleta.clase2.model.evento
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_recordatorios.view.*

/**
 * A simple [Fragment] subclass.
 */
class RecordatoriosFragment : Fragment() {

    val auth = FirebaseAuth.getInstance()
    val user = auth.currentUser

    val alleventos: MutableList<evento> = mutableListOf()
    val eventosNoRealizados : MutableList<evento> = mutableListOf()
    val eventosRealizados : MutableList<evento> = mutableListOf()
    lateinit var recordatoriosRVAdapter: RecordatoriosRVAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_recordatorios, container, false)

        recordatoriosRVAdapter = RecordatoriosRVAdapter(
            activity!!.applicationContext,
            eventosNoRealizados as ArrayList<evento>
        )

        root.rv_recordatorios.layoutManager = LinearLayoutManager(
            activity!!.applicationContext,
            RecyclerView.VERTICAL,
            false
        )
        root.rv_recordatorios.setHasFixedSize(true)

        root.rv_recordatorios.adapter = recordatoriosRVAdapter

        root.fab_newEvent.setOnClickListener{
            val intent = Intent(context, AddEventActivity::class.java)
            startActivityForResult(intent,123)
        }

        root.fab_completados.setOnClickListener {
            recordatoriosRVAdapter = RecordatoriosRVAdapter(
                activity!!.applicationContext,
                eventosRealizados as ArrayList<evento>
            )
            root.rv_recordatorios.adapter = recordatoriosRVAdapter
        }

        root.fab_pendientes.setOnClickListener {
            recordatoriosRVAdapter = RecordatoriosRVAdapter(
                activity!!.applicationContext,
                eventosNoRealizados as ArrayList<evento>
            )
            root.rv_recordatorios.adapter = recordatoriosRVAdapter
        }
        return root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode==123 && resultCode==Activity.RESULT_CANCELED){
            Log.d("resultado","cancelado")
        }
        if(requestCode==1 && resultCode==Activity.RESULT_OK) {
            Log.d("resultado","guardado")
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onResume() {
        super.onResume()
        cargarEventos()
    }

    private fun cargarEventos() {
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("eventos")

        alleventos.clear()
        eventosRealizados.clear()
        eventosNoRealizados.clear()
        recordatoriosRVAdapter.notifyDataSetChanged()

        // Read from the database
        myRef.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                alleventos.clear()
                eventosRealizados.clear()
                eventosNoRealizados.clear()
                for (snapshot in dataSnapshot.children) {
                    if(snapshot.child("idu").getValue()==user!!.uid){
                        val evento = snapshot.getValue(evento::class.java)
                        if(evento!!.notificacion) eventosRealizados.add(evento)
                        else eventosNoRealizados.add(evento)
                        alleventos.add(evento)
                    }
                }
                recordatoriosRVAdapter.notifyDataSetChanged()
                Log.d("eventos",alleventos[0].nombre)
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("ListaEventos", "Failed to read value.", error.toException())
            }
        })

    }




}
