package com.example.yemeksiparis2.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.yemeksiparis2.model.Yemek
import com.example.yemeksiparis2.repository.YemekRepository

class YemekViewModel : ViewModel() {

    private val repository = YemekRepository()

    val yemekListesi: LiveData<List<Yemek>> = repository.yemekleriGetir()

    init {
        Log.d("yemekvm", "ViewModel init çalıştı")
        repository.yemekleriGetir()
    }
    fun yemekleriYenile() {
        // Eğer repository içinde böyle bir fonksiyon varsa, çağırabilirsin
        repository.yemekleriGetir()
    }
}

