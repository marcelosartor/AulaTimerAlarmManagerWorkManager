package br.com.msartor.aulatimeralarmmanagerworkmanager

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.icu.util.Calendar

class Agendamento(private val context: Context) {

    private lateinit var alarmManager: AlarmManager

    fun agendar(){
        val intent = Intent(context, AgendamentoBroadcastReceiver::class.java)

        val calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            //set(2025,9,27)
            add(Calendar.SECOND, 10)
        }
        //alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager = context.getSystemService(AlarmManager::class.java)
        alarmManager.set(
            AlarmManager.RTC_WAKEUP,
            //System.currentTimeMillis() + 5000,
            calendar.timeInMillis,
            PendingIntent.getBroadcast(
                context,
                1,
                intent,
                PendingIntent.FLAG_IMMUTABLE)
        )
    }

    fun cancelar(){}

}