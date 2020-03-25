package com.alejandrazuleta.clase2.ui.Calculadora


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.alejandrazuleta.clase2.R
import com.google.android.material.tabs.TabLayout


class CalculadoraFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_calculadora, container, false)
        val viewPager: ViewPager = view.findViewById(R.id.view_pager)
        val adapter = SectionsPagerAdapter(context!!,childFragmentManager)
        viewPager.adapter = adapter
        val tabs: TabLayout = view.findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)
        return view
    }

}
