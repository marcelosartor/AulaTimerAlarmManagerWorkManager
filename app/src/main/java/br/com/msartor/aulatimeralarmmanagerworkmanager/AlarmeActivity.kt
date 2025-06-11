package br.com.msartor.aulatimeralarmmanagerworkmanager

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.com.msartor.aulatimeralarmmanagerworkmanager.databinding.ActivityAlarmeBinding

class AlarmeActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityAlarmeBinding.inflate(layoutInflater)
    }
    private val agendamento by lazy { Agendamento(this) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
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