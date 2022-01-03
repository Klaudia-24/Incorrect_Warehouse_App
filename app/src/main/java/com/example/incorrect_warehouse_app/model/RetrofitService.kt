package com.example.incorrect_warehouse_app.model

import retrofit2.Call
import retrofit2.http.GET

interface RetrofitService {

    @GET("products")
    fun getAllProducts(): Call<List<Product>>

}