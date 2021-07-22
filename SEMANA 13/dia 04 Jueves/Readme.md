# Evidencia Jueves 22 de Julio (día 04 / Semana 13)

#### Leonardo Rodenas Escobar 

---

## ___Reflexión:___ 
No muy motivante, se sigue con lo de clases anteriores en Kotlin. Por ahora toca seguir aprendiendo con videos de youtube por el momento.

---

## ___Actividad 01:___
 Buscar información sobre como realizar el control switch, el spinner y el Intent (cambio de activity).

Realicé la calculadora de ejercicios anteriores, con lo que se pedia en el enunciado, pero ahora en Kotlin, aquí el código.

~~~Kotlin
package com.example.sextaappswitch
import android.content.Intent
import android.os.Bundle
import android.transition.TransitionManager
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
class MainActivity : AppCompatActivity() {
    private var tv1: TextView? = null
    private var tv2: TextView? = null
    private var et1: EditText? = null
    private var et2: EditText? = null
    private var sw1: Switch? = null
    private var sw2: Switch? = null
    private var sp1: Spinner? = null
    private var tContainer: ViewGroup? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tContainer = findViewById<View>(R.id.transitionContainer) as ViewGroup
        tv1 = findViewById<View>(R.id.id_TextView1) as TextView
        tv2 = findViewById<View>(R.id.id_TextView2) as TextView
        et1 = findViewById<View>(R.id.id_editTextNumber1) as EditText
        et2 = findViewById<View>(R.id.id_editTextNumber2) as EditText
        sw1 = findViewById<View>(R.id.id_Switch1) as Switch
        sw2 = findViewById<View>(R.id.id_Switch2) as Switch
        sp1 = findViewById<View>(R.id.id_Spinner) as Spinner
        val options = arrayOf("Suma", "Resta", "Multiplicar", "Dividir")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, options)
        sp1!!.adapter = adapter
        sw2!!.setOnClickListener(object : View.OnClickListener {
            var visible = false
            override fun onClick(view: View) {
                TransitionManager.beginDelayedTransition(tContainer)
                visible = !visible
                tv2!!.visibility = if (visible) View.VISIBLE else View.GONE
            }
        })
    }
    //boton
    fun Boton(view: View?) {
        val valor1_String = et1!!.text.toString()
        val valor2_String = et2!!.text.toString()
        val valor1_int = valor1_String.toInt()
        val valor2_int = valor2_String.toInt()
        val select = sp1!!.selectedItem.toString()
        if (select == "Suma") {
            val suma = valor1_int + valor2_int
            val result = suma.toString()
            tv2!!.text = result
        } else if (select == "Resta") {
            val resta = valor1_int - valor2_int
            val result = resta.toString()
            tv2!!.text = result
        } else if (select == "Multiplicar") {
            val mult = valor1_int * valor2_int
            val result = mult.toString()
            tv2!!.text = result
        } else if (select == "Dividir") {
            if (valor2_int != 0) {
                val div = valor1_int / valor2_int
                val result = div.toString()
                tv2!!.text = result
            } else {
                Toast.makeText(this, "El 2do valor tiene que ser != a 0", Toast.LENGTH_LONG).show()
            }
        }
    }
}
~~~

---

Ese es mi no muy avance de hoy :expressionless: muchas gracias !!!







