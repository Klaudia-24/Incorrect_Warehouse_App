package com.example.incorrect_warehouse_app.model

import retrofit2.Call
import retrofit2.http.*

interface RetrofitService {

    @GET("products")
    fun getAllProducts(): Call<List<Product>>

//    @GET("signIn")
//    fun isSignInSuccessful(
//        @Field("login") login: String,
//        @Field("password") password: String
//    ): Call<CurrentUser>

    @POST("signIn")
    fun isSignInSuccessful(@Body signInRequest: SignInRequest): Call<String>

//    @POST("signIn")
//    fun isSignInSuccessful(@Body signInRequest: SignInRequest): Call<String>

    @POST("currentUser")
    fun getCurrentUser(@Body login: String): Call<CurrentUser>
}