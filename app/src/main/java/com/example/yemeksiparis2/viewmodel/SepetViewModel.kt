package com.example.yemeksiparis2.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.yemeksiparis2.model.SepetYemek
import com.example.yemeksiparis2.repository.YemekRepository

class SepetViewModel : ViewModel() {

    private val repository = YemekRepository()

    val sepetYemekleri: LiveData<List<SepetYemek>> = repository.sepettekiYemekleriGetir()

    // DetayFragment için: Basit parametrelerle ekleme
    fun sepeteYemekEkle(
        yemekAdi: String,
        yemekResimAdi: String,
        yemekFiyat: Any,
        yemekAdet: Int,
        kullaniciAdi: String
    ) {
        // SepetYemek objesi oluşturulup repository'ye gönderilir
        val sepetYemek = SepetYemek(
            sepet_yemek_id = "0", // varsayılan, API bunu atayacaktır
            yemek_id = "0", // veya gerçek ID
            yemek_adi = yemekAdi,
            yemek_fiyat = yemekFiyat.toString(),
            yemek_resim_adi = yemekResimAdi,
            yemek_siparis_adet = yemekAdet,
            kullanici_adi = kullaniciAdi
        )
        yemekEkle(sepetYemek)

    }

    // SepetFragment için var olan fonksiyon
    fun yemekEkle(sepetYemek: SepetYemek) {
        repository.sepeteYemekEkle(
            sepetYemek.yemek_id,
            sepetYemek.yemek_adi,
            sepetYemek.yemek_fiyat,
            sepetYemek.yemek_resim_adi,
            sepetYemek.yemek_siparis_adet
        )
    }

    fun yemekSil(sepet_yemek_id: String) {
        repository.sepettenYemekSil(sepet_yemek_id)
    }
}
