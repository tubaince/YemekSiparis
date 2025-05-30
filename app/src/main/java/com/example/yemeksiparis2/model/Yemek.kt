package com.example.yemeksiparis2.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Yemek(
    val yemek_id: String,
    val yemek_adi: String,
    val yemek_resim_adi: String,
    val yemek_fiyat: Int,
    val yemek_siparis_adet: Int
) : Parcelable