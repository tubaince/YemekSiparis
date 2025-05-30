package com.example.yemeksiparis2.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.yemeksiparis2.model.SepetYemek
import com.example.yemeksiparis2.repository.YemekRepository
import java.util.UUID

class SepetViewModel : ViewModel() {

    private val repository = YemekRepository()

    val sepetYemekleri: LiveData<MutableList<SepetYemek>> = repository.sepettekiYemekleriGetir()

    fun sepeteYemekEkle(
        yemekId: String,
        yemekAdi: String,
        yemekResimAdi: String,
        yemekFiyat: String,
        yemekAdet: Int,
        kullaniciAdi: String
    ) {
        val sepetYemekId = UUID.randomUUID().toString()

        val yeniSepetYemek = SepetYemek(
            sepet_yemek_id = sepetYemekId,
            yemek_id = yemekId,
            yemek_adi = yemekAdi,
            yemek_resim_adi = yemekResimAdi,
            yemek_fiyat = yemekFiyat,
            yemek_siparis_adet = yemekAdet,
            kullanici_adi = kullaniciAdi
        )

        repository.sepeteYemekEkle(yeniSepetYemek)
    }

    fun sepettenYemekSil(yemekAdi: String) {
        repository.sepettenYemekSil(yemekAdi)
    }
}
