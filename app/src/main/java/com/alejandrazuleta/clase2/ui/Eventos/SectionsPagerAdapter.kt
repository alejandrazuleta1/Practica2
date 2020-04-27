package com.alejandrazuleta.clase2.ui.Eventos

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.alejandrazuleta.clase2.R
import com.alejandrazuleta.clase2.ui.Eventos.recordatorios.RecordatoriosFragment

private val TAB_TITLES = arrayOf(
    R.string.recordatorios,
    R.string.consultarcitacion,
    R.string.calendarioac
)

class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        when (position){
            0 -> return RecordatoriosFragment()
            1 -> return FechacitacionFragment()
            else -> return CalendarioFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        // Show 3 total pages.
        return 3
    }
}