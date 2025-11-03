package br.com.msartor.aulatimeralarmmanagerworkmanager

import android.icu.util.Calendar
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
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


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnAgendamento.setOnClickListener {
            agendamento.agendar()
        }

        binding.btnCancelarAgendamento.setOnClickListener {

        }


    }
}