package com.alejandrazuleta.clase2.ui.Eventos.recordatorios


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
    lateinit var recordatoriosRVAdapter: RecordatoriosRVAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_recordatorios, container, false)

        recordatoriosRVAdapter = RecordatoriosRVAdapter(
            activity!!.applicationContext,
            alleventos as ArrayList<evento>
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

        return root
    }

    override fun onResume() {
        super.onResume()
        cargarEventos()
    }

    private fun cargarEventos() {
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("eventos")

        alleventos.clear()

        // Read from the database
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (snapshot in dataSnapshot.children) {
                    if(snapshot.child("idusuario").getValue()==user!!.uid){
                        var evento = snapshot.child("idusuario").getValue(evento::class.java)
                        alleventos.add(evento!!)
                    }
                }
                recordatoriosRVAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("ListaEventos", "Failed to read value.", error.toException())
            }
        })

    }


}
