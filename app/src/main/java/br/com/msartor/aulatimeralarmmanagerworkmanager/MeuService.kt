package br.com.msartor.aulatimeralarmmanagerworkmanager

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MeuService : Service() {

    val coroutineScope = CoroutineScope(Dispatchers.IO)

    override fun onBind(intent: Intent): IBinder? {
       return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val idCanal = "Lembretes"
        val notificationManager = applicationContext.getSystemService(NotificationManager::class.java)

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val canal = NotificationChannel(idCanal,"Lembretes", NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(canal)
        }

        val notificacaoBuilder = NotificationCompat.Builder(applicationContext,idCanal).apply {
            setSmallIcon(R.drawable.ic_lembrete)
            setShowWhen(true)
            setContentTitle("Lembrete")
            setContentText("Lembre-se de fazer alguma coisa")
        }

        startForeground(1,notificacaoBuilder.build())


        execurtarAcao()
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        coroutineScope.cancel()
        super.onDestroy()
    }

    private fun execurtarAcao() {
        coroutineScope.launch {
            repeat(5) { contador ->
                delay(1000)
                Log.i("AGENDAMENTO_ANDROID", "Servico Contador: $contador")
            }
        }
    }
}