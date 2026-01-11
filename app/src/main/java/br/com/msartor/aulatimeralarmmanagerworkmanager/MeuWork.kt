package br.com.msartor.aulatimeralarmmanagerworkmanager

import android.content.Context
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import br.com.msartor.aulatimeralarmmanagerworkmanager.api.CustomRetrofit
import kotlinx.coroutines.delay
import kotlin.math.log

class MeuWork(private val context: Context, private val workerParameters: WorkerParameters) : CoroutineWorker(context, workerParameters) {
    override suspend fun doWork(): Result {
        //executarAcao()
        //return Result.success()

        return  executarAcaoAPI()
    }

    private suspend fun executarAcaoAPI(): Result {

        setForeground(
            ForegroundInfo(
                System.currentTimeMillis().toInt(),
                NotificationCompat.Builder(applicationContext, Constantes.ID_CANAL)
                    .setSmallIcon(R.drawable.ic_lembrete)
                    .setShowWhen(true)
                    .setContentTitle("Ultimas Postagens")
                    .setContentText("Recuperando postagem da Web")
                    .build()
            )

        )

        val jsonPlaceHolderApi = CustomRetrofit.jsonPlaceApi
        val response = jsonPlaceHolderApi.recuperarPostagens()
        if (response.isSuccessful) {
            response.body().let {listPostage ->
                listPostage?.forEach { postage ->
                    delay(250)
                    Log.i("workmenager_android", "Id: ${postage.id} Titulo: ${postage.title}")
                }
            }
            return Result.success(workDataOf("result" to "Sucesso"))
        }else{
            if(response.code().toString().startsWith("5")) {
                return Result.retry()
            }
        }
        return Result.failure(workDataOf("result" to "Falha"))
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
        Log.i("workmenager_android-parametros", "Nome: ${nome} Tempo: ${time}" )

        setProgress( workDataOf(Constantes.PROGRESS_WORK to 0 ))

        repeat(100){
            delay(1000)
            //Log.i("workmenager_android", "Servico Contador: $it")
            setProgress( workDataOf(Constantes.PROGRESS_WORK to it ))
        }
    }
}