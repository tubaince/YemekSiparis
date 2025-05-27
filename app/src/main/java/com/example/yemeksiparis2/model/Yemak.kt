package com.example.yemeksiparis2.model

import java.io.Serializable

data class Yemek(
    val yemek_adi: String,
    val yemek_resim_adi: String,
    val yemek_fiyat: Int
) : java.io.Serializable
