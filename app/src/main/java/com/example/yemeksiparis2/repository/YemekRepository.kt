package com.example.yemeksiparis2.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.yemeksiparis2.model.Yemek
import com.example.yemeksiparis2.model.SepetYemek
import com.example.yemeksiparis2.network.RetrofitClient
import com.example.yemeksiparis2.model.YemekCevap
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class YemekRepository {

    // Sepetteki yemeklerin tutulduğu MutableLiveData, başlangıçta boş liste
    private val _sepetYemekleri = MutableLiveData<MutableList<SepetYemek>>(mutableListOf())
    val sepetYemekleri: LiveData<MutableList<SepetYemek>> = _sepetYemekleri

    // Sunucudan yemek listesini getir (network işlemi)
    fun yemekleriGetir(): LiveData<List<Yemek>> {
        val data = MutableLiveData<List<Yemek>>()
        RetrofitClient.apiService.tumYemekleriGetir().enqueue(object : Callback<YemekCevap> {
            override fun onResponse(call: Call<YemekCevap>, response: Response<YemekCevap>) {
                Log.d("YemekRepository", "HTTP Kodu: ${response.code()}")
                if (response.isSuccessful) {
                    data.value = response.body()?.yemekler ?: emptyList()
                } else {
                    data.value = emptyList()
                }
            }

            override fun onFailure(call: Call<YemekCevap>, t: Throwable) {
                Log.e("YemekRepository", "Yemekler alınamadı: ${t.message}", t)
                data.value = emptyList()
            }
        })
        return data
    }

    // Sepete yemek ekleme - aynı yemek varsa adet artırılır
    fun sepeteYemekEkle(yemek: SepetYemek) {
        val liste = _sepetYemekleri.value ?: mutableListOf()

        val mevcutYemek = liste.find { it.yemek_adi == yemek.yemek_adi }
        if (mevcutYemek != null) {
            val index = liste.indexOf(mevcutYemek)
            val yeniAdet = mevcutYemek.yemek_siparis_adet + yemek.yemek_siparis_adet
            liste[index] = mevcutYemek.copy(yemek_siparis_adet = yeniAdet)
            Log.d("YemekRepository", "Sepetteki ${yemek.yemek_adi} adet güncellendi: $yeniAdet")
        } else {
            liste.add(yemek)
            Log.d("YemekRepository", "Sepete yeni yemek eklendi: ${yemek.yemek_adi}")
        }

        // LiveData'yı güncelle
        _sepetYemekleri.value = liste
    }

    // Sepetteki yemekleri LiveData olarak döndürür
    fun sepettekiYemekleriGetir(): LiveData<MutableList<SepetYemek>> = sepetYemekleri

    // Sepetten yemek sil (yemek adı ile)
    fun sepettenYemekSil(yemekAdi: String) {
        val liste = _sepetYemekleri.value ?: mutableListOf()
        val yeniListe = liste.filter { it.yemek_adi != yemekAdi }.toMutableList()
        _sepetYemekleri.value = yeniListe
        Log.d("YemekRepository", "Sepetten yemek silindi: $yemekAdi")
    }

    // Sepeti tamamen temizle (opsiyonel)
    fun sepetiTemizle() {
        _sepetYemekleri.value = mutableListOf()
        Log.d("YemekRepository", "Sepet temizlendi")
    }
}
