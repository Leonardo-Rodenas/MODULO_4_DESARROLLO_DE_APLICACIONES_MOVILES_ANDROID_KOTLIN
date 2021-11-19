package com.example.ejemploretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ejemploretrofit.adapter.MascotaAdapter
import com.example.ejemploretrofit.databinding.ActivityMainBinding
import com.example.ejemploretrofit.viewmodel.MascotaViewModel
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    lateinit var b:ActivityMainBinding
    lateinit var viewModel:MascotaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)

        viewModel = ViewModelProvider(this).get(MascotaViewModel::class.java)
        val adapter = MascotaAdapter()
        with(b)
        {
            rvLista.layoutManager = LinearLayoutManager(this@MainActivity)

            rvLista.adapter = adapter
            viewModel.buscarMascota("akita")


            svBuscar.setOnQueryTextListener(object:SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    if (query!!.isNotEmpty())
                    {
                        viewModel.buscarMascota(query)
                    }
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }

            })


        }


        viewModel.mascota.observe(this, Observer {

            try{
                adapter.setMascota(it.message)
            }
            catch (ex:Exception)
            {
                Log.e("MI_ERROR", ex.message.toString())
                Toast.makeText(this,"Raza no existe",Toast.LENGTH_SHORT).show()
            }

        })

    }
}