package com.example.hilosycontador

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var contador = 0
    var estado = true
    var pausa = false

    override fun onCreate(savedInstanceState: Bundle?) {

        var hilo = Hilo(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tvnumero = findViewById<TextView>(R.id.tvnumero)
        val btniniciar = findViewById<Button>(R.id.btniniciar)
        btniniciar.setOnClickListener {
            hilo.start()
        }
        val btnpausar = findViewById<Button>(R.id.btnpausar)
        btnpausar.setOnClickListener {
            pausa = true
        }
        val btncontinuar = findViewById<Button>(R.id.btncontinuar)
        btncontinuar.setOnClickListener {
            pausa = false
        }
        val btnreiniciar = findViewById<Button>(R.id.btnreiniciar)
        btnreiniciar.setOnClickListener {
            contador = 0
        }
        val btndetener = findViewById<Button>(R.id.btndetener)
        btndetener.setOnClickListener setOnclikListener@{
            if (hilo.isAlive) {
                estado = false
                return@setOnclikListener
            }
        }
    }

    class Hilo(activity: MainActivity) : Thread() {
        var act = activity
        override fun run() {
            super.run()
            while (act.estado) {
                while (act.pausa == true) {
                    sleep(100)
                }
                sleep(100)
                act.runOnUiThread {
                    act.tvnumero.setText("Hilo: " + act.contador)
                }
                act.contador++
            }
        }
    }
}