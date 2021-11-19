package com.example.ejemploretrofit.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ejemploretrofit.R
import com.example.ejemploretrofit.databinding.ItemLayoutBinding

class MascotaAdapter : RecyclerView.Adapter<MascotaAdapter.CustomViewHolder>() {

    private var lista: ArrayList<String> = ArrayList()

    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        private val b = ItemLayoutBinding.bind(itemView)
        fun bindData(img:String)
        {
            Glide.with(itemView).load(img).into(b.ivMascota)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.item_layout,parent,false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bindData(lista[position])
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    fun setMascota(mascotas:List<String>)
    {
        lista = mascotas as ArrayList<String>
        notifyDataSetChanged()
    }
}