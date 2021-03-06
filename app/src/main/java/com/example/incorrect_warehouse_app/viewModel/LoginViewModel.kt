package com.example.incorrect_warehouse_app.viewModel

import androidx.lifecycle.ViewModel
import com.example.incorrect_warehouse_app.model.*
import com.example.incorrect_warehouse_app.utils.HashString
import com.example.incorrect_warehouse_app.utils.RetrofitObject
import com.example.incorrect_warehouse_app.utils.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel: ViewModel() {

    private val retrofitObject = RetrofitObject.getRetrofitInstance().create(RetrofitService::class.java)

    fun signInUser(login: String, password: String, onResult: (Boolean)->Unit){

        val hashedPassword = HashString.hash(password)
        val signInRequest = SignInRequest(login, hashedPassword)

        retrofitObject.isSignInSuccessful(signInRequest).enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {

                onResult(response.body().toBoolean())
            }
            override fun onFailure(call: Call<String>, t: Throwable) {

                onResult(false)
            }
        })
    }

    fun getCurrentUser(login: String, onResult: (CurrentUser?)->Unit){

        retrofitObject.getCurrentUser(login).enqueue(object : Callback<CurrentUser?> {
            override fun onResponse(call: Call<CurrentUser?>, response: Response<CurrentUser?>) {

                onResult(response.body())
            }
            override fun onFailure(call: Call<CurrentUser?>, t: Throwable) {

                onResult(null)
            }
        })
    }

}