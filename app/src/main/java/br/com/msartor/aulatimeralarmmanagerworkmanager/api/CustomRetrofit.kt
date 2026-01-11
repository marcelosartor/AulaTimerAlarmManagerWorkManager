package br.com.msartor.aulatimeralarmmanagerworkmanager.api

import androidx.annotation.RawRes
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object CustomRetrofit {
    val jsonPlaceApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(JsonPlaceHolderApi::class.java)
    }

}