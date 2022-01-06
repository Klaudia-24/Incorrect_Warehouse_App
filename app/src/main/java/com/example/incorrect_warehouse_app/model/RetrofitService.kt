package com.example.incorrect_warehouse_app.model

import retrofit2.Call
import retrofit2.http.*

interface RetrofitService {

    @POST("signIn")
    fun isSignInSuccessful(@Body signInRequest: SignInRequest): Call<String>

    @POST("currentUser")
    fun getCurrentUser(@Body login: String): Call<CurrentUser>

    // PRODUCTS
    @GET("products")
    fun getAllProducts(): Call<List<Product>>

    @POST("products")
    fun addNewProduct(@Body product: Product): Call<String>

    @PUT("products")
    fun modifyProduct(@Body product: Product): Call<String>

    @DELETE("products")
    fun deleteProduct(@Body productid: Int): Call<String>

    //RESERVATIONS
    @GET("reservation")
    fun getAllReservations(): Call<List<Reservation>>

    //EMPLOYEES
}