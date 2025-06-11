package br.com.msartor.aulatimeralarmmanagerworkmanager

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class AgendamentoBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        Log.i("AGENDAMENTO_ANDROID", "Excecutou broadcast agendamento ")
    }
}