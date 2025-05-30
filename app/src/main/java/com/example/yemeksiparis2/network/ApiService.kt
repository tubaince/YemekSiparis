package com.example.yemeksiparis2.network

import com.example.yemeksiparis2.model.YemekCevap
import com.example.yemeksiparis2.model.SepetYemek
import retrofit2.Call
import retrofit2.http.*
import retrofit2.http.GET

interface ApiService {

    @GET("tumYemekleriGetir.php")
    fun tumYemekleriGetir(): Call<YemekCevap>

    @FormUrlEncoded
    @POST("sepeteYemekEkle.php")
    fun sepeteYemekEkle(
        @Field("yemek_id") yemek_id: String,
        @Field("yemek_adi") yemek_adi: String,
        @Field("yemek_fiyat") yemek_fiyat: String,
        @Field("yemek_resim_adi") yemek_resim_adi: String,
        @Field("yemek_siparis_adet") yemek_siparis_adet: Int,
        @Field("kullanici_adi") kullanici_adi: String = "deneme"
    ): Call<Void>

    @FormUrlEncoded
    @POST("sepettekiYemekleriGetir.php")
    fun sepettekiYemekleriGetir(
        @Field("kullanici_adi") kullanici_adi: String = "deneme"
    ): Call<List<SepetYemek>>

    @FormUrlEncoded
    @POST("sepettenYemekSil.php")
    fun sepettenYemekSil(
        @Field("sepet_yemek_id") sepet_yemek_id: String,
        @Field("kullanici_adi") kullanici_adi: String = "deneme"
    ): Call<Void>
}
