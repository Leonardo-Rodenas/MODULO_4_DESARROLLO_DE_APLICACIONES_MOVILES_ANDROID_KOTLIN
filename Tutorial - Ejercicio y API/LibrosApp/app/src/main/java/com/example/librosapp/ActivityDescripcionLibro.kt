package com.example.librosapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.librosapp.databinding.ActivityDescripcionLibroBinding
import com.example.librosapp.databinding.ActivityMainBinding
import com.example.librosapp.datosdeAPI.googleBooks
import kotlinx.android.synthetic.main.activity_descripcion_libro.*

class ActivityDescripcionLibro : AppCompatActivity() {

    private lateinit var binding: ActivityDescripcionLibroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDescripcionLibroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        datoslibro()

    }

    private fun datoslibro() {

        binding.titulolibro
        binding.imagenlibro
        binding.nombreautor
        binding.nombreeditorial
        binding.Numpaginas
        binding.NombreCategoria
        binding.descripcionlibro
        binding.ratingBar

        val bundle: Bundle? = intent.extras
        val titulo = bundle!!.getString("titulo")
        val imagen = bundle.getString("imagen")
        val autor = bundle.getString("autor")
        val editorial = bundle.getString("editorial")
        val numeropaginas = bundle.getInt("paginas")
        val categoria = bundle.getString("categoria")
        val descriplibro = bundle.getString("descripcion")
        val valoracion = bundle.getDouble("rating")

        try {

            titulolibro.text = titulo
            Glide.with(imagenlibro.context)
                .load(imagen)
                .fitCenter()
                .into(imagenlibro)
            nombreautor.text = autor
            nombreeditorial.text = editorial
            Numpaginas.text = numeropaginas.toString()
            NombreCategoria.text = categoria
            descripcionlibro.text = descriplibro
            //ratingBar.text = valoracion.toString()

        } catch (e: NullPointerException) {

            titulolibro.text = "Desconocido"
            imagenlibro.setImageResource(R.mipmap.noimagendisponible)
            nombreautor.text = "Desconocido"
            nombreeditorial.text = "Desconocida"
            Numpaginas.text = "Desconocido"
            NombreCategoria.text = "Desconocido"
            descripcionlibro.text = "Sin Descripción"

        }
        //poner en el gradle esta opcion compileOptions.encoding "ISO-8859-1" --> no funciona
/*ERROR EN MOSTRAR IMAGEN Y DESCRIPCION, ADEMAS DE QUE SE CAE AL SEGUNDO LIBRO (RECIBE INT EN UN NULL??)*/
    }

    override fun onCreateOptionsMenu(menu: Menu?):Boolean{
        menuInflater.inflate(R.menu.menuvolver, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id:Int = item.itemId

        if (id == R.id.volver) {
            //Toast.makeText(this, "Volver a libros", Toast.LENGTH_SHORT).show()
            val i : Intent = Intent(this, MainActivity::class.java)
            startActivity(i)
        }
        return super.onOptionsItemSelected(item)

    }
}