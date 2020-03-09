package com.alejandrazuleta.clase2.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alejandrazuleta.clase2.R
import com.alejandrazuleta.clase2.model.cursoinscrito
import com.google.firebase.database.FirebaseDatabase

/**
 * A simple [Fragment] subclass.
 */
class NotasFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_notas, container, false)
/*

        var cursosinsList: MutableList<cursoinscrito> = ArrayList()
        //  val rv_superheroes: RecyclerView = view.findViewById(R.id.rv_superheroes)
        view.rv_superheroes.setHasFixedSize(true)
        view.rv_superheroes.layoutManager = LinearLayoutManager(
            activity?.applicationContext,
            RecyclerView.VERTICAL,
            false
        )

        val superheroesRVAdapter = SuperheroesRVAdapter(
            activity!!.applicationContext,
            superheroesList as ArrayList
        )

        view.rv_superheroes.adapter = superheroesRVAdapter


*/
        return view
    }


}
