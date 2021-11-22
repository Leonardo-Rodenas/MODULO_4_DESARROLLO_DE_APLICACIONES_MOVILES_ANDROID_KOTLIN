package cl.duoc.ejemploroom.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import cl.duoc.ejemploroom.adapter.MensajeAdapter
import cl.duoc.ejemploroom.databinding.ActivityMainBinding
import cl.duoc.ejemploroom.model.Mensaje
import cl.duoc.ejemploroom.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    lateinit var model:MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        model = ViewModelProvider(this).get(MainViewModel::class.java)
        val adapter = MensajeAdapter()
        with(binding)
        {
            rvLista.layoutManager = LinearLayoutManager(this@MainActivity)
            rvLista.adapter = adapter
            adapter.setOnItemClickListener(object : MensajeAdapter.OnItemClickListener{
                override fun onMensajeClick(mensaje: Mensaje) {
                    //programo lo que hago al hacer click en cada item
                    //Toast.makeText(this@MainActivity,mensaje.contenido,Toast.LENGTH_LONG).show()
                    model.onItemClicked(mensaje)
                }
            })

            fbAgregar.setOnClickListener{
                val intento = Intent(this@MainActivity,AgregarActivity::class.java)
                startActivity(intento)
            }

        }

        model.mensajes!!.observe(this, Observer {
            adapter.setMensaje(it)
        })
    }
}