# Evidencia Marates 20 de Julio (día 02 / Semana 13)

#### Leonardo Rodenas Escobar 

----

## ___Reflexión___: 

Se comienza un nuevo módulo, se parte un poco lento con cosas básicas pero se comprende la razón, Kotlin es en esencia mas simple que Java (creo??), pero aun acostumbrarse a la nueva forma de escribir las cosas en un poco complejo, me va a tocar ver más videos y aprender un poco previo a las siguientes clases para entender todo.

---

## ___Apuntes de Kotlin___:

### __KOTLIN__:

+ Se usan el mismo tipo de datos primitivos (int, char, long, float, double, boolean, etc).
+ var edad : int = 15 → si es var es variable
+ val edad : int = 16 → si es val es constante
+ float → va con la f en la variable (para identificarlo)
+ double → no es necesario poner la letra como en float 
+ Método (Java) = Función (Kotlin) (son lo mismo, solo cambia el nombre de un lenguaje a otro)
+ En Kotlin el “;” final se puede omitir (en Java se te olvida = muchos errores)
 
__Ejemplo de método en Kotlin__
~~~Kotlin  
fun: funcion (v : view) String {

return

}

fun funcion (v : view) {

Toast.makeText(context: this, text: “Hola”, Toast.LENGTH_LONG).show()

}
~~~
</br>

+ El método text hace la misma función de get y de set que se hacia en Java
+ Recordar: en gradle module, poner la siguiente extensión id 'kotlin-android-extensions'
+ Usar el setOnClickListenner --> para asignar la función onClick en el código sin necesidad de dársela en el programa despues
  
---


## ___Actividad 01___: 
Usando la sintaxis Kotlin, declarar variables y hacer los Toast que las muestren por pantalla.

## ___Actividad 02___: 
Usando Kotlin, hacer una calculadora básica que sirva para hacer una operación básica.                      

    Ambas actividades se muestran juntas en la misma activity, pues comparten funciones
</br>
</br>
### ___Código:___

~~~Kotlin
package com.example.iniciokotlin
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

var edad : Int = 18
var Nombre : String = "Gokú"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btncalcular.setOnClickListener {
            var valor1 = ednum1.text.toString().toInt()
            var valor2 = ednum2.text.toString().toInt()
            var suma=valor1+valor2
            tvresultado.text="La suma:  ${suma.toString()}"

        }
    }

    fun edad (v : View) {
        Toast.makeText(this,"Tu edad es: "+ edad, Toast.LENGTH_LONG).show()
    }

    fun nombre (v : View) {
        Toast.makeText(this,"Tu nombre es: "+ Nombre, Toast.LENGTH_LONG).show()
    }

}

~~~
</br>
</br>
![imagen](https://ibb.co/BqgCvMw)
</br>
</br>
## ___Actividad 03___: 
Se continua avanzando el proyecto y se concluye el Markdown para subir como presentación (incluido en el repositorio de módulo 03 y subido al aula como evaluación para el módulo).

---

Eso concluye mi avancé en el nuevo módulo el dái de hoy, muchas gracias :smile: !!!

