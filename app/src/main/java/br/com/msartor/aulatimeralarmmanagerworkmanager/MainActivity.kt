package br.com.msartor.aulatimeralarmmanagerworkmanager

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.com.msartor.aulatimeralarmmanagerworkmanager.databinding.ActivityMainBinding
import java.util.Timer
import java.util.TimerTask

class MainActivity : AppCompatActivity() {

    private val biding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private lateinit var timer: Timer;
    private lateinit var countDownTimer: CountDownTimer;
    private var counter: Int = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(biding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        /* timer.schedule */
        biding.btnIniciar.setOnClickListener {
            Log.i("TESTE", "onClick Iniciar: ")
            countDownTimer = object : CountDownTimer(5000, 10) {
                override fun onTick(millisUntilFinished: Long) {
                    runOnUiThread {
                        biding.textContador.text = millisUntilFinished.toString()
                    }
                }

                override fun onFinish() {
                    biding.textContador.text = "Finalizado"
                }

            }
            countDownTimer.start()
        }

        /* timer.schedule
        biding.btnIniciar.setOnClickListener {
            timer = Timer()
            counter = 0;
            timer.schedule(
                object : TimerTask() {
                    override fun run() {
                        runOnUiThread {
                            biding.textContador.text = "Executou a tarefa ${++counter}"
                        }
                    }
                }, 2000, 2000
            )
        }

        biding.btnParar.setOnClickListener{
            biding.textContador.text = "Finalizou a tarefa "
            timer.cancel()
        }
        */
    }

    override fun onDestroy() {
        super.onDestroy()
        timer.cancel()
    }
}