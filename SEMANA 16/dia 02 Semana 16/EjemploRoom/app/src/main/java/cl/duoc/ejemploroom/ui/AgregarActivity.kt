package cl.duoc.ejemploroom.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import cl.duoc.ejemploroom.MensajeApp
import cl.duoc.ejemploroom.R
import cl.duoc.ejemploroom.databinding.ActivityAgregarBinding
import cl.duoc.ejemploroom.model.Mensaje
import cl.duoc.ejemploroom.viewmodel.MainViewModel

class AgregarActivity : AppCompatActivity() {
    lateinit var binding:ActivityAgregarBinding
    lateinit var model:MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityAgregarBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        model = ViewModelProvider(this).get(MainViewModel::class.java)
        supportActionBar?.setHomeAsUpIndicator(android.R.drawable.ic_delete)
        title = "Agregar Producto"
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val mInflate = menuInflater.inflate(R.menu.agregar_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId)
        {
            R.id.mAgregarItem->{
                //Aquí programo el agregar
                agregar()
                return true
            }
            else->{
                return super.onOptionsItemSelected(item)
            }
        }
    }

    private fun agregar()
    {
        with(binding)
        {
            val contendido = txtMensaje.text.toString()
            val mensaje = Mensaje(contendido)
            model.onAgregarClicked(mensaje)
            onBackPressed()
        }
    }
}