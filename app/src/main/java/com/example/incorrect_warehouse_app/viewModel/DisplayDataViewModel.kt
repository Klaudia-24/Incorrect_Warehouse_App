package com.example.incorrect_warehouse_app.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.incorrect_warehouse_app.model.Product
import com.example.incorrect_warehouse_app.model.RetrofitInstance
import com.example.incorrect_warehouse_app.model.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DisplayDataViewModel: ViewModel() {

    var productList = MutableLiveData<List<Product>>()

    fun getProductsData(){
        val retrofitService = RetrofitInstance.getRetrofitInstance().create(RetrofitService::class.java)

        retrofitService.getAllProducts().enqueue(object : Callback<List<Product>> {
            override fun onResponse(
                call: Call<List<Product>>,
                response: Response<List<Product>>
            ){
                //success
                productList.value = response.body()
            }

            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                // failure
            }
        })
    }
}