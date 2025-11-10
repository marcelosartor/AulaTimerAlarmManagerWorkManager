package br.com.msartor.aulatimeralarmmanagerworkmanager

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class ReinicializacaoReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if(intent.action == Intent.ACTION_BOOT_COMPLETED ){
            Log.i("AGENDAMENTO_ANDROID", "Reiniciou... ")
            val agendamento = Agendamento(context)
            agendamento.agendar()
        }
    }
}