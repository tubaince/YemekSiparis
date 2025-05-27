package com.example.yemeksiparis2.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.yemeksiparis2.model.Yemek
import com.example.yemeksiparis2.model.SepetYemek
import com.example.yemeksiparis2.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class YemekRepository {

    fun yemekleriGetir(): LiveData<List<Yemek>> {
        val data = MutableLiveData<List<Yemek>>()
        RetrofitClient.apiService.tumYemekleriGetir().enqueue(object : Callback<List<Yemek>> {
            override fun onResponse(call: Call<List<Yemek>>, response: Response<List<Yemek>>) {
                if (response.isSuccessful) {
                    data.value = response.body() ?: emptyList()
                } else {
                    data.value = emptyList()
                }
            }

            override fun onFailure(call: Call<List<Yemek>>, t: Throwable) {
                data.value = emptyList()
            }
        })
        return data
    }

    fun sepeteYemekEkle(yemek_id: String, yemek_adi: String, yemek_fiyat: Int, yemek_resim_adi: String, adet: Int) {
        RetrofitClient.apiService.sepeteYemekEkle(yemek_id, yemek_adi, yemek_fiyat, yemek_resim_adi, adet).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                // Başarılı ekleme işlemi için gerekirse işlem yapılabilir
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                // Hata yönetimi yapılabilir
            }
        })
    }

    fun sepettekiYemekleriGetir(): LiveData<List<SepetYemek>> {
        val data = MutableLiveData<List<SepetYemek>>()
        RetrofitClient.apiService.sepettekiYemekleriGetir().enqueue(object : Callback<List<SepetYemek>> {
            override fun onResponse(call: Call<List<SepetYemek>>, response: Response<List<SepetYemek>>) {
                if (response.isSuccessful) {
                    data.value = response.body() ?: emptyList()
                } else {
                    data.value = emptyList()
                }
            }

            override fun onFailure(call: Call<List<SepetYemek>>, t: Throwable) {
                data.value = emptyList()
            }
        })
        return data
    }

    fun sepettenYemekSil(sepet_yemek_id: String) {
        RetrofitClient.apiService.sepettenYemekSil(sepet_yemek_id).enqueue(object : retrofit2.Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                // Silme başarılıysa işlem yapılabilir
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                // Hata yönetimi yapılabilir
            }
        })
    }
}
