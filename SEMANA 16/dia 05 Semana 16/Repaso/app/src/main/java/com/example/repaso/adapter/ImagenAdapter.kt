package com.example.repaso.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.repaso.R
import com.example.repaso.databinding.ImagenesItemLayoutBinding
import com.example.repaso.model.MascotaJson

class ImagenAdapter : RecyclerView.Adapter<ImagenAdapter.CustomViewHolder>() {

    private var lista:List<String> = ArrayList()

    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var b = ImagenesItemLayoutBinding.bind(itemView)
        fun bindData(url: String)
        {
            with(b)
            {
                Glide.with(itemView).load(url).into(ivPerrito)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.imagenes_item_layout,parent,false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bindData(lista[position])
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    fun setLista(lista:List<String>)
    {
        this.lista = lista
        notifyDataSetChanged()
    }
}