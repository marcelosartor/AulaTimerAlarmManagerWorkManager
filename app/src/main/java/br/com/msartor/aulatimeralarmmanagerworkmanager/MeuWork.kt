package br.com.msartor.aulatimeralarmmanagerworkmanager

import android.content.Context
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.WorkerParameters
import kotlinx.coroutines.delay

class MeuWork(private val context: Context, private val workerParameters: WorkerParameters) : CoroutineWorker(context, workerParameters) {
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

        val nome = workerParameters.inputData.getString(Constantes.PARAMETER_WORK_NAME)
        val time = workerParameters.inputData.getInt(Constantes.PARAMETER_WORK_TIME,0)

        Log.i("workmenager_android", "Nome: ${nome} Tempo: ${time}" )

        repeat(5){
            delay(1000)
            Log.i("workmenager_android", "Servico Contador: $it")
        }
    }
}