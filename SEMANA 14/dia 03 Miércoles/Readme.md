# Evidencia Miércoles 28 de Julio (día 03 / Semana 14)

#### Leonardo Rodenas Escobar 

---

## ___Reflexión:___

Esta clase fue muy fome, el ejercicio funcionaba perfectamente sin necesidad de implementar a los threads (hilos), por lo que hacerlo era netamente para forzar una instancia no necesaria en la aplicación. Por mi parte, preferí usar mi tiempo en algo más importante y hacer otras cosas (tutoriales de youtube) que seguir prestando atención a lo demás.

---

## ___Ejercicio 01:___

Hacer el factorial de un numero e intentar meterle hilos de ejecución.

![1](https://i.imgur.com/0XI1ODl.png)

~~~Kotlin
package com.example.factorialdeunnumero

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.example.factorialdeunnumero.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //VARIABLES
    private lateinit var binding: ActivityMainBinding
    lateinit var num : EditText
    lateinit var result : TextView
    //var fact=true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //var hilo = Hilo(this)

        binding.calcular.setOnClickListener {
        calcular()
        }
    }

    fun factorial (n:Float):Float{
        var dato:Float=1F
        for(i in 1 .. n.toInt()){
            dato*=i
        }
        return dato
    }

    fun calcular (){
        var resultadoF=factorial(numero.text.toString().toFloat())
        resultado.setText(resultadoF.toString())
    }
}
~~~
---
Eso, hay termina hoy, con cero motivación por hacer estos ejericicios.