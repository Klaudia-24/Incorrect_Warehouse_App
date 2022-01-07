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

    @DELETE("products/{id}")
    fun deleteProduct(@Path("id") id: Int): Call<String>

    // RESERVATIONS
    @GET("reservations")
    fun getAllReservations(): Call<List<Reservation>>

    @POST("reservations")
    fun addNewReservation(@Body reservation: Reservation): Call<String>

    @PUT("reservations")
    fun modifyReservation(@Body reservation: Reservation): Call<String>

    @DELETE("reservations/{id}")
    fun deleteReservation(@Path("id") id: Int): Call<String>

    // EMPLOYEES
    @GET("employees")
    fun getAllEmployees(): Call<List<Employee>>

    @POST("employees")
    fun addNewEmployee(@Body employee: Employee): Call<String>

    @PUT("employees")
    fun modifyEmployee(@Body employee: Employee): Call<String>

    @DELETE("employees/{id}")
    fun deleteEmployee(@Path("id") id: Int): Call<String>
}