package br.com.msartor.aulatimeralarmmanagerworkmanager

import android.content.pm.PackageManager
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.com.msartor.aulatimeralarmmanagerworkmanager.databinding.ActivityAlarmeBinding
import java.text.SimpleDateFormat

class AlarmeActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityAlarmeBinding.inflate(layoutInflater)
    }
    private val agendamento by lazy { Agendamento(this) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        val calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(2025,9,27)
        }

        val dataMillis = calendar.timeInMillis
        val formatador = SimpleDateFormat("D w W dd/MM/yyyy HH:mm:ss")
        val dataFormatada = formatador.format(dataMillis)
        Log.i("AGENDAMENTO_ANDROID","Data: $dataFormatada")

        solicitarPermissao()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnAgendamento.setOnClickListener {
            Log.i("AGENDAMENTO_ANDROID","Cliclou no botao agendamento...")
            agendamento.agendar()
        }

        binding.btnCancelarAgendamento.setOnClickListener {

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