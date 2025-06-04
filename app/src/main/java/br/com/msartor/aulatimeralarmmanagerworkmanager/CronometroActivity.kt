package br.com.msartor.aulatimeralarmmanagerworkmanager

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.com.msartor.aulatimeralarmmanagerworkmanager.databinding.ActivityCronometroBinding

class CronometroActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityCronometroBinding.inflate(layoutInflater)
    }
    private lateinit var countDownTimer: CountDownTimer;
    private var executandoCronometro: Boolean = false;
    private var tempoMilisegundos: Long = 60000L;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnIniciarCronometro.setOnClickListener {
            if (executandoCronometro) {
                pausarCronometro()
            }else{
                val tempoDigitado = binding.editTempo.text.toString()
                if (tempoDigitado.isNotEmpty()) {
                    tempoMilisegundos = tempoDigitado.toLong() * 60000L
                }
                iniciarCronometro(tempoMilisegundos)
            }
        }

        binding.btnReiniciarCronometro.setOnClickListener {
            reiniciarCronometro()
        }



    }

    private fun reiniciarCronometro() {
        tempoMilisegundos = 60000L
        binding.textResultado.text = "00:00"
        binding.btnReiniciarCronometro.visibility = View.INVISIBLE
    }

    private fun iniciarCronometro(tempoMilisegundosParam: Long) {
        countDownTimer = object : CountDownTimer(tempoMilisegundosParam, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                tempoMilisegundos = millisUntilFinished
                atualizarCronometro()
            }

            override fun onFinish() {
                binding.textResultado.text = "Finalizado"
            }

        }
        countDownTimer.start()
        binding.btnIniciarCronometro.text = "Pausar Crônometro"
        executandoCronometro = true
        binding.btnReiniciarCronometro.visibility = View.INVISIBLE
    }

    private fun atualizarCronometro() {
        val minutos = (tempoMilisegundos / 1000) / 60
        val segundos = (tempoMilisegundos / 1000) % 60
        binding.textResultado.text = String.format("%02d:%02d", minutos, segundos)
        //binding.textResultado.text = "$minutos:$segundos"
    }

    private fun pausarCronometro() {
        binding.btnIniciarCronometro.text = "Iniciar Crônometro"
        executandoCronometro = false
        binding.btnReiniciarCronometro.visibility = View.VISIBLE
        countDownTimer.cancel()
    }

    override fun onStop() {
        super.onStop()
        countDownTimer.cancel()

    }
}