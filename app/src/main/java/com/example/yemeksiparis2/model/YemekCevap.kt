package com.example.yemeksiparis2.model

data class YemekCevap(
    val success: Int,
    val message: String,
    val yemekler: List<Yemek>
)