package com.example.fragments

import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    val taxiFragment = TaxiFragment()
    val avionFragment = AvionFragment()
    val barcoFragment = BarcoFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottom_navigation=findViewById<BottomNavigationView>(R.id.bottom_navigation)
        reemplazar(taxiFragment)
        bottom_navigation.setOnItemSelectedListener {

            when(it.itemId){
                R.id.taxi -> reemplazar(taxiFragment)
                R.id.avion -> reemplazar(avionFragment)
                R.id.bote -> reemplazar(barcoFragment)
            };true
        }
    }

    fun reemplazar (fragment: Fragment) {

        if (fragment !=null){
            val transaccion = supportFragmentManager.beginTransaction()
            transaccion.replace(R.id.fragment_container,fragment)
            transaccion.commit()
            Toast.makeText(this, "Cambiar fragment", Toast.LENGTH_SHORT).show()
        }

    }

}