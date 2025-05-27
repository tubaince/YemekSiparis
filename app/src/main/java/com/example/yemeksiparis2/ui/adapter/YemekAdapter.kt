package com.example.yemeksiparis2.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.yemeksiparis2.databinding.YemekRowBinding
import com.example.yemeksiparis2.model.Yemek

class YemekAdapter(
    private val yemekListesi: List<Yemek>,
    private val onItemClick: (Yemek) -> Unit
) : RecyclerView.Adapter<YemekAdapter.YemekViewHolder>() {

    inner class YemekViewHolder(val binding: YemekRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(yemek: Yemek) {
            binding.yemekAdiText.text = yemek.yemek_adi
            binding.yemekFiyatText.text = "${yemek.yemek_fiyat} ₺"

            // Resim URL'si tam olarak API dökümantasyonuna göre değişebilir
            val resimUrl = "http://kasimadalan.pe.hu/yemekler/resimler/${yemek.yemek_resim_adi}"
            Glide.with(binding.root.context)
                .load(resimUrl)
                .into(binding.yemekResimImage)

            binding.root.setOnClickListener {
                onItemClick(yemek)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YemekViewHolder {
        val binding = YemekRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return YemekViewHolder(binding)
    }

    override fun onBindViewHolder(holder: YemekViewHolder, position: Int) {
        holder.bind(yemekListesi[position])
    }

    override fun getItemCount(): Int = yemekListesi.size
}
