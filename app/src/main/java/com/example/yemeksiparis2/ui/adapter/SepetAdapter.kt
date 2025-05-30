package com.example.yemeksiparis2.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.yemeksiparis2.databinding.SepetRowBinding
import com.example.yemeksiparis2.model.SepetYemek

class SepetAdapter(
    private var sepetYemekListesi: List<SepetYemek>
) : RecyclerView.Adapter<SepetAdapter.SepetViewHolder>() {

    inner class SepetViewHolder(val binding: SepetRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(sepetYemek: SepetYemek) {
            binding.yemekAdiText.text = sepetYemek.yemek_adi
            binding.yemekAdetText.text = "Adet: ${sepetYemek.yemek_siparis_adet}"
            binding.yemekFiyatText.text = "${sepetYemek.yemek_fiyat} ₺"

            val url = "http://kasimadalan.pe.hu/yemekler/resimler/${sepetYemek.yemek_resim_adi}"
            Glide.with(binding.root.context).load(url).into(binding.yemekResimImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SepetViewHolder {
        val binding = SepetRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SepetViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SepetViewHolder, position: Int) {
        holder.bind(sepetYemekListesi[position])
    }

    override fun getItemCount(): Int = sepetYemekListesi.size

    fun updateSepetList(newList: List<SepetYemek>) {
        sepetYemekListesi = newList
        notifyDataSetChanged()
    }
}
