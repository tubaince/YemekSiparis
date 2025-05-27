package com.example.yemeksiparis2.model

data class SepetYemek(
    val sepet_yemek_id: String,
    val yemek_id: String,
    val yemek_adi: String,
    val yemek_resim_adi: String,
    val yemek_fiyat: Int,
    val yemek_siparis_adet: Int,
    val kullanici_adi: String
)
