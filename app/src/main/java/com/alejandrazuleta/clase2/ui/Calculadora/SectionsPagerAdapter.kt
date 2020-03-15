package com.alejandrazuleta.clase2.ui.Calculadora

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.alejandrazuleta.clase2.R

private val TAB_TITLES = arrayOf(
    R.string.notafinalcurso,
    R.string.promsemestre
)

class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        when (position){
            0 -> return NotaFinalCursoFragment()
            else -> return PromedioSemestreFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        // Show 2 total pages.
        return 2
    }
}