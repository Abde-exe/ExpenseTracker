package com.example.expensetracker.api

import com.example.expensetracker.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val api :MoneyChangeApi by lazy {
        Retrofit.Builder()
            .baseUrl("http://api.exchangeratesapi.io/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MoneyChangeApi::class.java)
    }
}