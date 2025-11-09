package br.com.msartor.aulatimeralarmmanagerworkmanager

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.icu.util.Calendar
import android.os.Build
import android.util.Log

class Agendamento(private val context: Context) {

    private lateinit var alarmManager: AlarmManager
    private lateinit var pendingIntent: PendingIntent

    fun agendar(){
        //val intent = Intent(context, AgendamentoBroadcastReceiver::class.java)
        val intent = Intent(context, MeuService::class.java)

        val calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            //set(2025,9,27)
            //add(Calendar.SECOND, 10)
        }
        val calendarInterval = Calendar.getInstance().apply {
            set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY)
        }

        pendingIntent = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            PendingIntent.getForegroundService(
                context,
                1,
                intent,
                PendingIntent.FLAG_IMMUTABLE
            )
        } else {
            PendingIntent.getService(
                context,
                1,
                intent,
                PendingIntent.FLAG_IMMUTABLE)
        }

        //alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager = context.getSystemService(AlarmManager::class.java)

        alarmManager.setInexactRepeating(
            AlarmManager.RTC,
            System.currentTimeMillis()+4_000,
            30_000,
            pendingIntent
        )

        /*alarmManager.set(
            AlarmManager.RTC_WAKEUP,
            //System.currentTimeMillis() + 5000,
            calendar.timeInMillis,
            PendingIntent.getBroadcast(
                context,
                1,
                intent,
                PendingIntent.FLAG_IMMUTABLE)
        )
        alarmManager.setInexactRepeating(
            AlarmManager.RTC,
            calendar.timeInMillis,
            //calendarInterval.timeInMillis
            //AlarmManager.INTERVAL_FIFTEEN_MINUTES,
            120000,
            PendingIntent.getBroadcast(
                context,
                1,
                intent,
                PendingIntent.FLAG_IMMUTABLE)
        )
         */
    }

    fun cancelar(){
        Log.i("AGENDAMENTO_ANDROID","Cancelado")
        alarmManager.cancel(pendingIntent)
        Log.i("AGENDAMENTO_ANDROID", "Agendamento cancelado com sucesso!")
    }

}