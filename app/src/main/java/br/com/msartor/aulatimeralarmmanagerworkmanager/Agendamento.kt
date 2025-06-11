package br.com.msartor.aulatimeralarmmanagerworkmanager

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent

class Agendamento(private val context: Context) {

    private lateinit var alarmManager: AlarmManager

    fun agendar(){
        val intent = Intent(context, AgendamentoBroadcastReceiver::class.java)
        //alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager = context.getSystemService(AlarmManager::class.java)
        alarmManager.set(
            AlarmManager.RTC_WAKEUP,
            System.currentTimeMillis() + 5000,
            PendingIntent.getBroadcast(context,1,intent, PendingIntent.FLAG_IMMUTABLE))
    }

    fun cancelar(){}

}