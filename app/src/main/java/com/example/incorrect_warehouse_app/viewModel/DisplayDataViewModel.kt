package com.example.incorrect_warehouse_app.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.incorrect_warehouse_app.model.Product
import com.example.incorrect_warehouse_app.model.RetrofitInstance
import com.example.incorrect_warehouse_app.model.RetrofitService
import com.example.incorrect_warehouse_app.model.SignInRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DisplayDataViewModel: ViewModel() {

    var productList = MutableLiveData<List<Product>>()
    var addedProductStatus: String? = null

    fun getProductsData(){
        val retrofitService = RetrofitInstance.getRetrofitInstance().create(RetrofitService::class.java)

        retrofitService.getAllProducts().enqueue(object : Callback<List<Product>> {
            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>){
                //success
                productList.value = response.body()
            }
            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                // failure
            }
        })
    }

    fun addNewProductData(newProduct: Product, onResult: (Boolean)->Unit){
        val retrofitService = RetrofitInstance.getRetrofitInstance().create(RetrofitService::class.java)

        retrofitService.addNewProduct(newProduct).enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>){

                onResult(response.body().toBoolean())
            }
            override fun onFailure(call: Call<String>, t: Throwable) {

                onResult(false)
            }
        })
    }

    fun modifyProduct(product: Product, onResult: (Boolean)->Unit){
        val retrofitService = RetrofitInstance.getRetrofitInstance().create(RetrofitService::class.java)

        retrofitService.modifyProduct(product).enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>){

                onResult(response.body().toBoolean())
            }
            override fun onFailure(call: Call<String>, t: Throwable) {

                onResult(false)
            }
        })
    }

    fun deleteProduct(productId: Int, onResult: (Boolean)->Unit){
        val retrofitService = RetrofitInstance.getRetrofitInstance().create(RetrofitService::class.java)

        retrofitService.deleteProduct(productId).enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>){

                Log.d("TEST deleteProduct:", "onResponse")
                onResult(response.body().toBoolean())
            }
            override fun onFailure(call: Call<String>, t: Throwable) {

                Log.d("TEST deleteProduct:", "onFailure")
                onResult(false)
            }
        })
    }
}