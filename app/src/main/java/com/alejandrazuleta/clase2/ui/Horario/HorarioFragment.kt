package com.alejandrazuleta.clase2.ui.Horario


import android.graphics.RectF
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alamkanak.weekview.MonthLoader
import com.alamkanak.weekview.WeekView
import com.alamkanak.weekview.WeekViewEvent
import com.alejandrazuleta.clase2.R
import kotlinx.android.synthetic.main.fragment_horario.*
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class HorarioFragment : Fragment(), WeekView.EventClickListener, MonthLoader.MonthChangeListener, WeekView.EventLongPressListener, WeekView.EmptyViewLongPressListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_horario, container, false)

        val mWeekView: WeekView = view.findViewById(R.id.weekView)
        mWeekView.setNumberOfVisibleDays(6);

        // Set an action when any event is clicked.
        mWeekView.setOnEventClickListener(this)

        // The week view has infinite scrolling horizontally. We have to provide the events of a
        // month every time the month changes on the week view.
        mWeekView.setMonthChangeListener(this)

        // Set long press listener for events.
        mWeekView.setEventLongPressListener(this)

        return view
    }

    override fun onEventClick(event: WeekViewEvent?, eventRect: RectF?) {

    }

    override fun onEventLongPress(event: WeekViewEvent?, eventRect: RectF?) {

    }

    override fun onEmptyViewLongPress(time: Calendar?) {

    }

    override fun onMonthChange(newYear: Int, newMonth: Int): MutableList<out WeekViewEvent> {
        val aux : MutableList<out WeekViewEvent> = ArrayList()
        return aux
    }
}

