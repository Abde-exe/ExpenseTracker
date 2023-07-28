package com.example.expensetracker.api

import android.content.Context
import android.util.Log
import com.example.expensetracker.models.CurrencyChange
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

const val api_key = "94e2c10772f3239c1b3e5a16ec833d81"


data class ExchangeRatesResponse(
    val base: String,
    val date: String,
    val rates: Map<String, Double>
)

interface MoneyChangeApi {


    @GET("&base=EUR&symbols=TRY")
    suspend fun getEURfromTRY(amount: Float): Float {

        return 0f
    }

    @GET("&base=EUR&symbols=TRY")
    suspend fun getTRYfromEUR(amount: Float): Float {

        return 0f
    }

    //function that uses the api to get the exchange rates
    @GET("latest?access_key=$api_key")
     fun getExchangeRates(
        @Query("base") base: String,
        @Query("symbols") symbols: String
    ): Call<ExchangeRatesResponse>

     @GET("latest?access_key=$api_key")
     fun getTest(
    ): Call<ExchangeRatesResponse>
}




fun getCurrencyData() : CurrencyChange = runBlocking {
    val baseSymbol = "EUR"
    val otherSymbol = "TRY"
    var currencyData: CurrencyChange? = null

    try {
        val response = withContext(Dispatchers.IO) {
            RetrofitInstance.api.getExchangeRates(baseSymbol, otherSymbol).execute()
        }

        if (response.isSuccessful) {
            val testObject = response.body()
            testObject?.let {
                currencyData = CurrencyChange(baseSymbol, otherSymbol, it.rates[otherSymbol]!!)
            }
        } else {
            // Handle API error if needed
        }
    } catch (e: Exception) {
        // Handle exceptions if needed
    }
Log.d("currencyData from api", currencyData?.rate.toString())
    return@runBlocking currencyData!!
}
