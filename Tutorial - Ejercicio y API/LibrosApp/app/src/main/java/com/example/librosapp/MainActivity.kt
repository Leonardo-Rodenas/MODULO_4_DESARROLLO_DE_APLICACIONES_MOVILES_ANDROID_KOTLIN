package com.example.librosapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.SearchEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.librosapp.databinding.ActivityMainBinding
import com.example.librosapp.datosdeAPI.Item
import com.example.librosapp.datosdeAPI.googleBooks
import kotlinx.android.synthetic.main.activity_descripcion_libro.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_libros.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Query
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() , SearchView.OnQueryTextListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.svlibros.setOnQueryTextListener(this)
        consultaDelUsuario("el hobbit")

    }

    private fun initRecyclerView(libros:googleBooks) {
    val librosAdapter = LibrosAdapter(libros)
    //adapter = librosAdapter
    binding.rvlibros.adapter = librosAdapter
    binding.rvlibros.layoutManager = LinearLayoutManager(this)
/*continuo acá con los clicklistener de la activity libros --> min 4:48 video 2*/

//se le pasa el object de la interfaz creada y se le implementan los miembros como se hace dando click en la ampolleta roja y seleccionando "implement members"
        librosAdapter.setOnItemClickListener(object : LibrosAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                //acá va lo de pasar a otra activity con los datos de los libros
                //Toast.makeText(this@MainActivity, "Selecionaste la card N° $position", Toast.LENGTH_SHORT).show()

                val titulo = libros.items[position].volumeInfo.title
                val autor = libros.items[position].volumeInfo.authors
                val editorial = libros.items[position].volumeInfo.publisher
                val numpaginas = libros.items[position].volumeInfo.pageCount
                val categoria = libros.items[position].volumeInfo.categories
                val descripcion = libros.items[position].volumeInfo.description
                val imagenlibro = libros.items[position].volumeInfo.imageLinks.smallThumbnail
                val rating = libros.items[position].volumeInfo.averageRating
                var primercategoria = "Desconocida"
                /*Estas dos daban errores porque son listas de String, entonces solo le paso el primer valor
                en posicion 0 para que no me de eses error*/

                val primerautor = autor[0]
                //val primercategoria = categoria[0]

                try{
                    var primercategoria = categoria[0]
                    var categoria = libros.items[position].volumeInfo.categories
                }catch(e: NullPointerException){
                    var categoria = "Desconocida"
                    var primercategoria = categoria
                }

                val pasardatos = Intent(this@MainActivity, ActivityDescripcionLibro::class.java). apply {

                    putExtra("titulo", titulo)
                    putExtra("imagen", imagenlibro)
                    putExtra("autor", primerautor)
                    putExtra("editorial", editorial)
                    putExtra("paginas", numpaginas)
                    putExtra("categoria", primercategoria)
                    putExtra("descripcion", descripcion)
                    putExtra("rating", rating)

                }
                startActivity(pasardatos) //de acá me voy a donde colocar los datos que voy a pasar minuto 6 del videos https://www.youtube.com/watch?v=EoJX7h7lGxM&t=64s
            }
        })
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://www.googleapis.com/books/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun consultaDelUsuario(query: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(APIService::class.java).buscarLibroPorTitulo(query)
            val libro = call.body()!!
            runOnUiThread {
                if (call.isSuccessful){
                Log.v("hola", call.body()!!.toString())
                initRecyclerView(libro)
                }else{
                    Toast.makeText(applicationContext, "Ha ocurrido un error", Toast.LENGTH_SHORT).show()
                }
                hideKeyboard()
                }
            }
        }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (!query.isNullOrEmpty()) {
            consultaDelUsuario(query.lowercase(Locale.getDefault()))
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }

    private fun hideKeyboard() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.root.windowToken, 0)
    }

    override fun onCreateOptionsMenu(menu:Menu?):Boolean{
        menuInflater.inflate(R.menu.menu_mandar_correo, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id:Int = item.itemId
        if (id == R.id.botoncorreo) {
            Toast.makeText(this, "Enviar correo", Toast.LENGTH_SHORT).show()
            val i : Intent = Intent(this, CorreoActivity::class.java)
            startActivity(i)
        }
        return super.onOptionsItemSelected(item)
    }

}
