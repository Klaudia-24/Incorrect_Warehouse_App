package com.example.incorrect_warehouse_app.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.incorrect_warehouse_app.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create
import java.security.MessageDigest

class LoginViewModel: ViewModel() {

    var currentUser: CurrentUser? = null
    private val retrofitService = RetrofitInstance.getRetrofitInstance().create(RetrofitService::class.java)

    fun signInUser(login: String, password: String, onResult: (Boolean)->Unit){

        val hashedPassword = hashString(password)
        val signInRequest = SignInRequest(login, hashedPassword)

        retrofitService.isSignInSuccessful(signInRequest).enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {

                onResult(response.body().toBoolean())
            }
            override fun onFailure(call: Call<String>, t: Throwable) {

                onResult(false)
            }
        })
    }

    fun getCurrentUser(login: String, onResult: (CurrentUser?)->Unit){

        retrofitService.getCurrentUser(login).enqueue(object : Callback<CurrentUser?> {
            override fun onResponse(call: Call<CurrentUser?>, response: Response<CurrentUser?>) {

                currentUser = response.body()!!
                onResult(response.body())
            }
            override fun onFailure(call: Call<CurrentUser?>, t: Throwable) {

                onResult(null)
            }
        })
    }

    private fun ByteArray.toHex(): String {
        return joinToString("") { "%02x".format(it) }
    }

    private fun String.toMD5(): String {
        val bytes = MessageDigest.getInstance("MD5").digest(this.toByteArray())
        return bytes.toHex()
    }

    fun hashString(input: String): String {
        return input.toMD5()
    }

}