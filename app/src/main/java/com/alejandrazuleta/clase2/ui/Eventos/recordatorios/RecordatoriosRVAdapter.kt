package com.alejandrazuleta.clase2.ui.Eventos.recordatorios

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alejandrazuleta.clase2.R
import com.alejandrazuleta.clase2.model.evento
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.item_recordatorios.view.*


class RecordatoriosRVAdapter(
    context: Context, eventosList: ArrayList<evento>
) : RecyclerView.Adapter<RecordatoriosRVAdapter.RecordatoriosViewHolder>() {

    private var eventosList = emptyList<evento>()
    private val context: Context

    init {
        this.eventosList = eventosList
        this.context = context
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecordatoriosViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.item_recordatorios, parent, false)
        return RecordatoriosViewHolder(
            itemView,
            context
        )
    }

    override fun getItemCount(): Int = eventosList.size

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun onBindViewHolder(
        holder: RecordatoriosViewHolder,
        position: Int
    ) {
        val evento: evento = eventosList[position]
        holder.bindCursoins(evento)
    }

    class RecordatoriosViewHolder(
        itemView: View, context: Context
    ) : RecyclerView.ViewHolder(itemView) {
        private var context:Context
        init {
            this.context = context
        }

        fun bindCursoins(evento: evento) {
            itemView.tv_titulo.text = evento.nombre
            itemView.tv_fechahora.text = evento.fecha + " " + evento.hora
            itemView.tv_curso.text = evento.nombrecurso
            if (evento.notificacion) itemView.active_image.setImageResource(R.drawable.ic_notifications_active_black_48dp)
            else  itemView.active_image.setImageResource(R.drawable.ic_notifications_off_black_48dp)

            itemView.setOnClickListener {
                var intent = Intent(context, DetalleEventoActivity::class.java)
                intent.putExtra("evento", evento).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(intent)
            }

            itemView.active_image.setOnClickListener {
                if (evento.notificacion) {
                    //eliminar alarma
                }
                else {
                    //encender la alarma
                }

                val database = FirebaseDatabase.getInstance()
                val myRef = database.getReference("eventos")
                val childUpdate = HashMap<String, Any>()
                childUpdate["notificacion"] = !evento.notificacion
                myRef.child(evento.id).updateChildren(childUpdate)
            }


        }

        private fun cancelAlarm(evento: evento) {
            /*
            val alarmManager : AlarmManager = Context.ALARM_SERVICE as AlarmManager
            val intent : Intent = Intent(context,AlarmReceiver::class.java)
            val pendingIntent: PendingIntent = PendingIntent.getBroadcast(context,1,intent,0)
            alarmManager.cancel(pendingIntent)

             */
        }

        private fun startAlarm(evento: evento) {
            /*
            val alarmManager : AlarmManager = Context.ALARM_SERVICE as AlarmManager
            val intent : Intent = Intent(context,AlarmReceiver::class.java)
            val pendingIntent: PendingIntent = PendingIntent.getBroadcast(context,1,intent,0)

            val date = evento.fecha.split("/")
            val hour = evento.hora.split(":")

            val cal : Calendar = Calendar.Builder().setCalendarType("iso8601")
                .setDate(date[2].toInt(),date[1].toInt(),date[0].toInt()).build()

            cal.set(Calendar.HOUR_OF_DAY,hour[0].toInt())
            cal.set(Calendar.MINUTE,hour[1].toInt())

            alarmManager.setExact(AlarmManager.RTC_WAKEUP,cal.timeInMillis,pendingIntent)

             */
        }
    }


}