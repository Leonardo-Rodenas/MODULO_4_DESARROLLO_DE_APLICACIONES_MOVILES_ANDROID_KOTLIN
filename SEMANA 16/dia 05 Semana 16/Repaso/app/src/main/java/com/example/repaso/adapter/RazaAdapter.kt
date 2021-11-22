package com.example.repaso.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.repaso.R
import com.example.repaso.databinding.RazaItemLayoutBinding
import com.example.repaso.model.RazaModel

class RazaAdapter : RecyclerView.Adapter<RazaAdapter.CustomViewHolder>() {

    var lista:List<RazaModel> = ArrayList()
    lateinit var listener:OnClickListener

    class CustomViewHolder(itemView: View,var listener: OnClickListener): RecyclerView.ViewHolder(itemView)
    {
        var b = RazaItemLayoutBinding.bind(itemView)
        fun bindData(razaModel: RazaModel)
        {
            with(b)
            {
                txtRaza.text = razaModel.descripcion
                itemView.setOnClickListener {
                    listener.onClick(razaModel)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.raza_item_layout,parent,false)
        return CustomViewHolder(v,listener)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bindData(lista[position])
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    fun setRaza(razas:List<RazaModel>)
    {
        lista = razas
        notifyDataSetChanged()
    }

    interface OnClickListener{
        fun onClick(razaModel: RazaModel)
    }


    fun setRazaListener(listener: OnClickListener)
    {
        this.listener = listener
    }


}