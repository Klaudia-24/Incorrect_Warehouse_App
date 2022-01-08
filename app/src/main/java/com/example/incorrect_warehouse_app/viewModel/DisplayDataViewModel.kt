package com.example.incorrect_warehouse_app.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.incorrect_warehouse_app.model.*
import com.example.incorrect_warehouse_app.utils.RetrofitInstance
import com.example.incorrect_warehouse_app.utils.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DisplayDataViewModel: ViewModel() {

    var productList = MutableLiveData<List<Product>>()
    var lowStockProductList = MutableLiveData<List<Product>>()
    var reservationList = MutableLiveData<List<Reservation>>()
    var employeeList = MutableLiveData<List<Employee>>()
    var employeeAdminList = MutableLiveData<List<EmployeeAdminData>>()
    var newEmployeeList = MutableLiveData<List<Employee>>()
    var roleList = MutableLiveData<List<Role>>()

    val retrofitService = RetrofitInstance.getRetrofitInstance().create(RetrofitService::class.java)

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

    fun getLowStockProductsData(){

        retrofitService.getLowStockProducts().enqueue(object : Callback<List<Product>> {
            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>){
                //success
                lowStockProductList.value = response.body()
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

        retrofitService.deleteProduct(id).enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>){

                onResult(response.body().toBoolean())
            }
            override fun onFailure(call: Call<String>, t: Throwable) {

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

    fun addNewReservationData(newReservation: NewReservation, onResult: (Boolean)->Unit){

        retrofitService.addNewReservation(newReservation).enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>){

                onResult(response.body().toBoolean())
            }
            override fun onFailure(call: Call<String>, t: Throwable) {

                onResult(false)
            }
        })
    }

    fun modifyReservation(reservation: Reservation, onResult: (Boolean)->Unit){

        retrofitService.modifyReservation(reservation).enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>){

                onResult(response.body().toBoolean())
            }
            override fun onFailure(call: Call<String>, t: Throwable) {

                onResult(false)
            }
        })
    }

    fun deleteReservation(id: Int, onResult: (Boolean)->Unit){

        retrofitService.deleteReservation(id).enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>){

                onResult(response.body().toBoolean())
            }
            override fun onFailure(call: Call<String>, t: Throwable) {

                onResult(false)
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

    fun addNewEmployeeData(newEmployee: Employee, onResult: (Boolean)->Unit){

        retrofitService.addNewEmployee(newEmployee).enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>){

                onResult(response.body().toBoolean())
            }
            override fun onFailure(call: Call<String>, t: Throwable) {

                onResult(false)
            }
        })
    }

    fun modifyEmployee(employee: Employee, onResult: (Boolean)->Unit){

        retrofitService.modifyEmployee(employee).enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>){

                onResult(response.body().toBoolean())
            }
            override fun onFailure(call: Call<String>, t: Throwable) {

                onResult(false)
            }
        })
    }

    fun deleteEmployee(id: Int, onResult: (Boolean)->Unit){

        retrofitService.deleteEmployee(id).enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>){

                onResult(response.body().toBoolean())
            }
            override fun onFailure(call: Call<String>, t: Throwable) {

                onResult(false)
            }
        })
    }

    fun getEmployeeDataAdmin(){

        retrofitService.getAllEmployeesAdmin().enqueue(object : Callback<List<EmployeeAdminData>> {
            override fun onResponse(call: Call<List<EmployeeAdminData>>, response: Response<List<EmployeeAdminData>>){
                //success
                employeeAdminList.value = response.body()
            }
            override fun onFailure(call: Call<List<EmployeeAdminData>>, t: Throwable) {
                // failure
            }
        })
    }

    fun getNewEmployeeDataAdmin(){

        retrofitService.getAllNewEmployeesAdmin().enqueue(object : Callback<List<Employee>> {
            override fun onResponse(call: Call<List<Employee>>, response: Response<List<Employee>>){
                //success
                newEmployeeList.value = response.body()
            }
            override fun onFailure(call: Call<List<Employee>>, t: Throwable) {
                // failure
            }
        })
    }

    fun addNewEmployeeDataAdmin(newEmployeeAdmin: EmployeeAdminData, onResult: (Boolean)->Unit){

        retrofitService.addNewEmployeeAdmin(newEmployeeAdmin).enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>){

                onResult(response.body().toBoolean())
            }
            override fun onFailure(call: Call<String>, t: Throwable) {

                onResult(false)
            }
        })
    }

    fun modifyEmployeeAdmin(employee: EmployeeAdminData, onResult: (Boolean)->Unit){

        retrofitService.modifyEmployeeAdmin(employee).enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>){

                onResult(response.body().toBoolean())
            }
            override fun onFailure(call: Call<String>, t: Throwable) {

                onResult(false)
            }
        })
    }

    fun addNewEmployeeAdminLogin(employeeLoginDataRequest: EmployeeLoginDataRequest, onResult: (Boolean)->Unit){

        retrofitService.addEmployeeAdminLogin(employeeLoginDataRequest).enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>){

                onResult(response.body().toBoolean())
            }
            override fun onFailure(call: Call<String>, t: Throwable) {

                onResult(false)
            }
        })
    }

    fun modifyEmployeeAdminLogin(employeeLoginDataRequest: EmployeeLoginDataRequest, onResult: (Boolean)->Unit){

        retrofitService.modifyEmployeeAdminLogin(employeeLoginDataRequest).enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>){

                onResult(response.body().toBoolean())
            }
            override fun onFailure(call: Call<String>, t: Throwable) {

                onResult(false)
            }
        })
    }

    fun deleteEmployeeAdmin(id: Int, onResult: (Boolean)->Unit){

        retrofitService.deleteEmployeeAdmin(id).enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>){

                onResult(response.body().toBoolean())
            }
            override fun onFailure(call: Call<String>, t: Throwable) {

                onResult(false)
            }
        })
    }

    // ROLE
    fun getRoleData(){

        retrofitService.getAllRoles().enqueue(object : Callback<List<Role>> {
            override fun onResponse(call: Call<List<Role>>, response: Response<List<Role>>){
                //success
                roleList.value = response.body()
            }
            override fun onFailure(call: Call<List<Role>>, t: Throwable) {
                // failure
            }
        })
    }
}