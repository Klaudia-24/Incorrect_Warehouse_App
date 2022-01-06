package com.example.incorrect_warehouse_app.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.incorrect_warehouse_app.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DisplayDataViewModel: ViewModel() {

    var productList = MutableLiveData<List<Product>>()
    var reservationList = MutableLiveData<List<Reservation>>()
    var employeeList = MutableLiveData<List<Employee>>()

    val retrofitService = RetrofitInstance.getRetrofitInstance().create(RetrofitService::class.java)

    var addedProductStatus: String? = null

    fun getProductsData(){

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

        retrofitService.modifyProduct(product).enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>){

                onResult(response.body().toBoolean())
            }
            override fun onFailure(call: Call<String>, t: Throwable) {

                onResult(false)
            }
        })
    }

    fun deleteProduct(id: Int, onResult: (Boolean)->Unit){

        Log.d("TEST deleteProduct id:", id.toString())

        retrofitService.deleteProduct(id).enqueue(object : Callback<String> {
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

    // RESERVATIONS

    fun getReservationsData(){

        retrofitService.getAllReservations().enqueue(object : Callback<List<Reservation>> {
            override fun onResponse(call: Call<List<Reservation>>, response: Response<List<Reservation>>){
                //success
                reservationList.value = response.body()
            }
            override fun onFailure(call: Call<List<Reservation>>, t: Throwable) {
                // failure
            }
        })
    }


    // EMPLOYEE

    fun getEmployeeData(){

        retrofitService.getAllEmployees().enqueue(object : Callback<List<Employee>> {
            override fun onResponse(call: Call<List<Employee>>, response: Response<List<Employee>>){
                //success
                employeeList.value = response.body()
            }
            override fun onFailure(call: Call<List<Employee>>, t: Throwable) {
                // failure
            }
        })
    }

}