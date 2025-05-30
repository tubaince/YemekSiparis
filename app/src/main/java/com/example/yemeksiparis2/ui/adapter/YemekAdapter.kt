package com.example.yemeksiparis2.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.yemeksiparis2.databinding.YemekRowBinding
import com.example.yemeksiparis2.model.Yemek

class YemekAdapter(
    private var yemekListesi: List<Yemek>,  // var yapıldı
    private val onItemClick: (Yemek) -> Unit
) : RecyclerView.Adapter<YemekAdapter.YemekViewHolder>() {

    init {
        android.util.Log.d("YemekAdapter", "Yemek listesi boyutu: ${yemekListesi.size}")
    }
    inner class YemekViewHolder(val binding: YemekRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(yemek: Yemek) {
            binding.yemekAdiText.text = yemek.yemek_adi
            binding.yemekFiyatText.text = "${yemek.yemek_fiyat} ₺"

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

    // Listeyi güncellemek için fonksiyon eklendi
    fun updateYemekList(newList: List<Yemek>) {
        yemekListesi = newList
        notifyDataSetChanged()
    }
}
