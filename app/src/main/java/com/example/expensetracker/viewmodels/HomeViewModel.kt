package com.example.expensetracker.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.expensetracker.api.getCurrencyData
import com.example.expensetracker.models.CurrencyChange

class HomeViewModel : ViewModel() {

    //RetrofitInstance.api.getExchangeRates("EUR", "TRY")

    //get the currency from the  shared prefs, if it is null get it from the api
    fun getCurrencyPref(context: Context): CurrencyChange? {
        val sharedPref = context.getSharedPreferences("sharedPref", Context.MODE_PRIVATE)


        var rate = sharedPref.getFloat("rateTRY", 0f)
        if (rate == 0f) {
            rate = getCurrencyData().rate.toFloat()
        }

        val editor = sharedPref.edit()
        editor.putFloat("rateTRY", rate)
        editor.apply()

        return CurrencyChange("EUR", "TRY", rate.toDouble())
    }

    fun getSpentPref(context: Context): Float {
        val sharedPref = context.getSharedPreferences("sharedPref", Context.MODE_PRIVATE)
        return sharedPref.getFloat("spent", 0f)
    }

    fun getTotalBudgetPref(context: Context): Float {
        val sharedPref = context.getSharedPreferences("sharedPref", Context.MODE_PRIVATE)
        return sharedPref.getFloat("totalBudget", 0f)
    }

}