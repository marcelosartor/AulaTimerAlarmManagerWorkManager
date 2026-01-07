package br.com.msartor.aulatimeralarmmanagerworkmanager

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.delay

class MeuWork(private val context: Context, private val params: WorkerParameters) : CoroutineWorker(context, params) {
    override suspend fun doWork(): Result {
        executarAcao()
        return Result.success()
    }

    private suspend fun executarAcao(){
        repeat(5){
            delay(1000)
            Log.i("workmenager_android", "Servico Contador: $it")
        }
    }
}