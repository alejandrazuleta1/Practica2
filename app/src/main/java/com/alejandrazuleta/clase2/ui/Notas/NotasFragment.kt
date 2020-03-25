package com.alejandrazuleta.clase2.ui.Notas

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alejandrazuleta.clase2.R
import com.alejandrazuleta.clase2.model.cursoinscrito
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_notas.view.*

class NotasFragment : Fragment() {

    val auth = FirebaseAuth.getInstance()
    val user = auth.currentUser

    val allCursosInscritos: MutableList<cursoinscrito> = mutableListOf()
    lateinit var notasRVAdapter: NotasRVAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_notas, container, false)

        notasRVAdapter = NotasRVAdapter(
            activity!!.applicationContext,
            allCursosInscritos as ArrayList<cursoinscrito>
        )

        root.rv_notas.layoutManager = LinearLayoutManager(
            activity!!.applicationContext,
            RecyclerView.VERTICAL,
            false
        )
        root.rv_notas.setHasFixedSize(true)

        root.rv_notas.adapter = notasRVAdapter

        return root
    }

    override fun onResume() {
        super.onResume()
        cargarCursosInscritos()
    }

    private fun cargarCursosInscritos() {

        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("cursosinscritos")

        allCursosInscritos.clear()

        // Read from the database
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (snapshot in dataSnapshot.children) {
                    if(snapshot.child("idusuario").child("idusuario").getValue()==user!!.uid){
                        var cursoins = snapshot.child("idusuario").getValue(cursoinscrito::class.java)
                        allCursosInscritos.add(cursoins!!)
                    }
                }
                notasRVAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("Lista", "Failed to read value.", error.toException())
            }
        })
    }
}