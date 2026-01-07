package br.com.msartor.aulatimeralarmmanagerworkmanager

import android.content.Context
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.WorkerParameters
import kotlinx.coroutines.delay

class MeuWork(private val context: Context, private val params: WorkerParameters) : CoroutineWorker(context, params) {
    override suspend fun doWork(): Result {
        executarAcao()
        return Result.success()
    }

    private suspend fun executarAcao(){

        setForeground(
            ForegroundInfo(
               System.currentTimeMillis().toInt(),
                NotificationCompat.Builder(applicationContext,Constantes.ID_CANAL)
                    .setSmallIcon(R.drawable.ic_lembrete)
                    .setShowWhen(true)
                    .setContentTitle("Lembrete")
                    .setContentText("Lembre-se de fazer alguma coisa via workmanager")
                    .build()
            )

        )

        repeat(20){
            delay(1000)
            Log.i("workmenager_android", "Servico Contador: $it")
        }
    }
}