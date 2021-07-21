# Evidencia día Miércoles 21 de Julio (dia 03 / Semana 13)

#### Leonardo Rodenas Escobar 

---

## ___Reflexión___: 
Trabajar de nuevo la calculador Radio Button que vimos hace semanas no es muy motivante, la verdad es mejor comenzar a avanzar el proyecto Quilterrier en Kotlin o ver video tutoriales en youtube, por mi parte esta clase no me gustó ni sentí algún progreso en mi aprendizaje.

---

## ___Actividad 01 y 02___: 

Calculadora Radio Button en Kotlin // CheckBox (por la similitud entre ambas aquí muestro con Radio Button, con el otro componente es igual solo que definiendo y considerando que donde se hace referencia al Radio Button, ahora debe ser CheckBox).

![asdfghjk](https://i.imgur.com/UzEnUGZ.png)

### __Código:__

~~~Kotlin
package com.example.iniciokotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_dia02_kotlin.*

class dia02Kotlin : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dia02_kotlin)

        BOTON.setOnClickListener {
            var valor1 = EDT1.text.toString().toInt()
            var valor2 = EDT2.text.toString().toInt()

            if (SUMA.isChecked) {
                var suma = valor1 + valor2
                RESULTADO.text = "La suma es: ${suma}"
            } else if (RESTA.isChecked) {
                var resta = valor1 - valor2
                RESULTADO.text = "La resta es: ${resta}"
            } else if (MULTI.isChecked) {
                var multi = valor1 * valor2
                RESULTADO.text = "La multiplicación es: ${multi}"
            } else if (DIVIDE.isChecked) {
                if (valor2 !== 0) {
                    var divi = valor1 / valor2
                    RESULTADO.text = "La división es: ${divi}"
                }else{
                    Toast.makeText(this,"No puedes dividir por 0", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    fun sumar() {
        var valor1 = EDT1.text.toString().toInt()
        var valor2 = EDT2.text.toString().toInt()
        var suma = valor1 + valor2
        RESULTADO.text = "La suma es: ${suma}"
    }

    fun restar() {
        var valor1 = EDT1.text.toString().toInt()
        var valor2 = EDT2.text.toString().toInt()
        var resta = valor1 - valor2
        RESULTADO.text = "La resta es: ${resta}"
    }

    fun multiplicar() {
        var valor1 = EDT1.text.toString().toInt()
        var valor2 = EDT2.text.toString().toInt()
        var multi = valor1 * valor2
        RESULTADO.text = "La multiplicación es: ${multi}"
    }

    fun dividir() {
        var valor1 = EDT1.text.toString().toInt()
        var valor2 = EDT2.text.toString().toInt()
        var divi = valor1 * valor2
        RESULTADO.text = "La división es: ${divi}"
    }
}
~~~
</br>

### ___Ojo___: 
Video de repaso consultado durante los tiempos muertos de clase, click aquí -->  [Mouredev](https://www.youtube.com/watch?v=ebQphhLpJG0)

---

Eso concluye mi avance (no muy avanzado) por el día de hoy :unamused: , muchas gracias!!!