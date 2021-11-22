package com.example.repaso

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.repaso.adapter.RazaAdapter
import com.example.repaso.databinding.ActivityMainBinding
import com.example.repaso.model.RazaModel
import com.example.repaso.viewmodel.ProyectoViewModel

class MainActivity : AppCompatActivity() {

    lateinit var b: ActivityMainBinding
    private val viewModel:ProyectoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)

        viewModel.callApi()

        val adapter = RazaAdapter()
        with(b)
        {
            rvListaRazas.layoutManager = LinearLayoutManager(this@MainActivity)
            rvListaRazas.adapter = adapter
        }


        adapter.setRazaListener(object : RazaAdapter.OnClickListener{
            override fun onClick(razaModel: RazaModel) {
                val intento = Intent(this@MainActivity,ImagenesActivity::class.java)
                intento.putExtra("raza",razaModel.descripcion)
                startActivity(intento)
            }

        })

        viewModel.razas.observe(this, Observer {
            adapter.setRaza(it)
        })
    }
}