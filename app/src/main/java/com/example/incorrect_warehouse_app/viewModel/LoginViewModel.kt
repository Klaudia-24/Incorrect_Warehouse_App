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

    var signInStatus: String? = null
    var currentUser: CurrentUser? = null
    private val retrofitService = RetrofitInstance.getRetrofitInstance().create(RetrofitService::class.java)

    fun signInUser(login: String, password: String, onResult: (Boolean)->Unit){

        Log.d("TEST","LoginViewModel: signInUser")

        val hashedPassword = hashString(password)
        Log.d("TEST", hashedPassword)

        val signInRequest = SignInRequest(login, hashedPassword)
        Log.d("TEST", signInRequest.toString())

        retrofitService.isSignInSuccessful(signInRequest).enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                Log.d("TEST","LoginViewModel: onResponse")
                Log.d("TEST body",response.body().toString())
                signInStatus = response.body().toString()
                Log.d("TEST signInStatus",signInStatus.toString())
                //currentUser = response.body()!!

                onResult(response.body().toBoolean())
            }
            override fun onFailure(call: Call<String>, t: Throwable) {
                //failure
                Log.d("TEST onFailure",t.message.toString())
                onResult(false)
            }
        })
    }

    fun getCurrentUser(login: String, onResult: (CurrentUser?)->Unit){

        Log.d("TEST login",login)

        retrofitService.getCurrentUser(login).enqueue(object : Callback<CurrentUser?> {
            override fun onResponse(call: Call<CurrentUser?>, response: Response<CurrentUser?>) {
                Log.d("TEST","getCurrentUser onResponse")
                Log.d("TEST body", response.body().toString())
                currentUser = response.body()!!
                onResult(response.body())
            }
            override fun onFailure(call: Call<CurrentUser?>, t: Throwable) {
                //failure
                Log.d("TEST","getCurrentUser onFailure")
                Log.d("TEST onFailure",t.message.toString())
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

//fun getProductsData(){
//    val retrofitService = RetrofitInstance.getRetrofitInstance().create(RetrofitService::class.java)
//
//    retrofitService.getAllProducts().enqueue(object : Callback<List<Product>> {
//        override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>){
//            //success
//            productList.value = response.body()
//        }
//        override fun onFailure(call: Call<List<Product>>, t: Throwable) {
//            // failure
//        }
//    })
//}