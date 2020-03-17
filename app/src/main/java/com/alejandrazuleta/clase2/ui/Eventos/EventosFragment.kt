package com.alejandrazuleta.clase2.ui.Eventos


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.alejandrazuleta.clase2.R
import com.google.android.material.tabs.TabLayout

/**
 * A simple [Fragment] subclass.
 */
class EventosFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_eventos, container, false)

        val viewPager: ViewPager = view.findViewById(R.id.view_pager)
        val adapter = SectionsPagerAdapter(context!!,childFragmentManager)
        viewPager.adapter = adapter
        val tabs: TabLayout = view.findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)
        return view
    }


}
