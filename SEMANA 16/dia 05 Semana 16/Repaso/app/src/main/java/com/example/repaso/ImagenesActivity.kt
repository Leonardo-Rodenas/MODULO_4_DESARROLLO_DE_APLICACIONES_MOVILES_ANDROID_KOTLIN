package com.example.repaso

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.repaso.adapter.ImagenAdapter
import com.example.repaso.databinding.ActivityImagenesBinding
import com.example.repaso.viewmodel.ProyectoViewModel

class ImagenesActivity : AppCompatActivity() {

    lateinit var b: ActivityImagenesBinding
    private val viewModel: ProyectoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityImagenesBinding.inflate(layoutInflater)
        setContentView(b.root)

        val raza = intent.getStringExtra("raza")

        viewModel.callImagenes(raza!!)

        var adapter = ImagenAdapter()

         with(b)
         {
             rvImagenes.layoutManager = LinearLayoutManager(this@ImagenesActivity)
             rvImagenes.adapter = adapter
         }

        viewModel.images.observe(this, Observer {
            adapter.setLista(it.message)
        })


    }
}