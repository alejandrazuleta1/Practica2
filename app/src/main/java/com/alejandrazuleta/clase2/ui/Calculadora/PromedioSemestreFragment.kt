package com.alejandrazuleta.clase2.ui.Calculadora


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.alejandrazuleta.clase2.R

/**
 * A simple [Fragment] subclass.
 */
class PromedioSemestreFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_promedio_semestre, container, false)
    }


}
