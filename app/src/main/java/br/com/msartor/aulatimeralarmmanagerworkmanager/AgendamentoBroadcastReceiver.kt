package br.com.msartor.aulatimeralarmmanagerworkmanager

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat

class AgendamentoBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        Log.i("AGENDAMENTO_ANDROID", "Excecutou broadcast agendamento ")
        exibirNotificacao(context)
    }

    private fun exibirNotificacao(context: Context) {
        val idCanal = "Lembretes"
        val notificationManager = context.getSystemService(NotificationManager::class.java)

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val canal = NotificationChannel(idCanal,"Lembretes",NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(canal)
        }

        val notificacaoBuilder = NotificationCompat.Builder(context,idCanal).apply {
            setSmallIcon(R.drawable.ic_lembrete)
            setShowWhen(true)
            setContentTitle("Lembrete")
            setContentText("Lembre-se de fazer alguma coisa")
        }

        val notificacao = notificacaoBuilder.build()
        notificationManager.notify(1,notificacao)

    }
}