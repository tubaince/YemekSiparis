package com.example.yemeksiparis2.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.yemeksiparis2.model.SepetYemek
import com.example.yemeksiparis2.repository.YemekRepository

class SepetViewModel : ViewModel() {

    private val repository = YemekRepository()

    private val _sepetYemekleri = MutableLiveData<List<SepetYemek>>()
    val sepetYemekleri: LiveData<List<SepetYemek>> = _sepetYemekleri

    // KullaniciAdi parametresi kaldırıldı
    fun getSepetYemekleri() {
        repository.sepettekiYemekleriGetir().observeForever { liste ->
            _sepetYemekleri.postValue(liste)
        }
    }

    fun sepeteYemekEkle(
        yemekAdi: String,
        yemekResimAdi: String,
        yemekFiyat: Int,
        yemekAdet: Int,
        kullaniciAdi: String
    ) {
        val sepetYemek = SepetYemek(
            sepet_yemek_id = "0",
            yemek_id = "0",
            yemek_adi = yemekAdi,
            yemek_fiyat = yemekFiyat.toString(),
            yemek_resim_adi = yemekResimAdi,
            yemek_siparis_adet = yemekAdet,
            kullanici_adi = kullaniciAdi
        )
        yemekEkle(sepetYemek)
    }

    fun yemekEkle(sepetYemek: SepetYemek) {
        repository.sepeteYemekEkle(
            sepetYemek.yemek_id,
            sepetYemek.yemek_adi,
            sepetYemek.yemek_fiyat,
            sepetYemek.yemek_resim_adi,
            sepetYemek.yemek_siparis_adet
        )
        // Listeyi güncelle
        getSepetYemekleri()
    }

    fun yemekSil(sepet_yemek_id: String) {
        repository.sepettenYemekSil(sepet_yemek_id)
        // Listeyi güncelle
        getSepetYemekleri()
    }
}
