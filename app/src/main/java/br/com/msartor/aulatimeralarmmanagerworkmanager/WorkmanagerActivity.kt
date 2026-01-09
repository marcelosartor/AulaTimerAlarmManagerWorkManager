package br.com.msartor.aulatimeralarmmanagerworkmanager

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.work.Constraints
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import br.com.msartor.aulatimeralarmmanagerworkmanager.databinding.ActivityWorkmanagerBinding

class WorkmanagerActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityWorkmanagerBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        solicitarPermissao()

        val oneTimeWorkRequest = OneTimeWorkRequestBuilder<MeuWork>()
            .setInputData(
                workDataOf(
                    Constantes.PARAMETER_WORK_NAME to "teste",
                    Constantes.PARAMETER_WORK_TIME to 1000
                )
            )
            .addTag(Constantes.TAG_WORK)
            .setConstraints(
                Constraints.Builder()
                    //.setRequiredNetworkType(NetworkType.CONNECTED)
                    //.setRequiresCharging(true)
                    //.setRequiresBatteryNotLow(true)
                    //.setRequiresDeviceIdle(true)
                    //.setRequiresStorageNotLow(true)
                    .build()
            )
            .build()

        val workManager = WorkManager.getInstance(applicationContext)

        binding.btnExecutarWork.setOnClickListener {
            //workManager.enqueue(oneTimeWorkRequest)
            workManager.enqueueUniqueWork(
                Constantes.NAME_UNIQUE_WORK,
                ExistingWorkPolicy.KEEP,
                oneTimeWorkRequest
            )
        }

        binding.btnCancelarWork.setOnClickListener{
            // workManager.cancelWorkById(oneTimeWorkRequest.id)
            // workManager.cancelAllWorkByTag(Constantes.TAG_WORK)
            workManager.cancelUniqueWork(Constantes.NAME_UNIQUE_WORK)
            // workManager.cancelAllWork()
        }
    }

    private fun solicitarPermissao() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            val permissaoNotificacao = ActivityCompat.checkSelfPermission(this,android.Manifest.permission.POST_NOTIFICATIONS)
            if(permissaoNotificacao == PackageManager.PERMISSION_DENIED) {
                requestPermissions(arrayOf(android.Manifest.permission.POST_NOTIFICATIONS), 10)
            }
        }
    }
}