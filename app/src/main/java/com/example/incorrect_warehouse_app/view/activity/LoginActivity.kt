package com.example.incorrect_warehouse_app.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.incorrect_warehouse_app.R
import com.example.incorrect_warehouse_app.model.PostgreSqlDatabase
import com.example.incorrect_warehouse_app.model.RetrofitService
import com.example.incorrect_warehouse_app.viewModel.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginActivity : AppCompatActivity() {
    companion object {
        val db = PostgreSqlDatabase()
    }

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)


//
//        val warehouseService: WarehouseService = retrofit.create(WarehouseService::class.java)

//        val warehouseService: WarehouseService = retrofit.create(WarehouseService::class.java)
//        warehouseService.getAllUsersRoles().enqueue(object: Callback<Any>{
//            override fun onResponse(call: Call<Any>, response: Response<Any>) {
//                Log.i("Login activity", response.toString())
//            }
//
//            override fun onFailure(call: Call<Any>, t: Throwable) {
//                Log.i("Login activity", t.message?: "Null message")
//            }
//        })



        signInButton.setOnClickListener setOnClickListner@{

            val userLogin = userLoginEditText.text.toString()
            val password = passwordEditText.text.toString()

//            if(userLogin.isEmpty()){
//                userLoginEditText.error = "Enter Your user name"
//                userLoginEditText.requestFocus()
//                return@setOnClickListner
//            }
//
//            if(password.isEmpty()){
//                passwordEditText.error = "Enter Your password"
//                passwordEditText.requestFocus()
//                return@setOnClickListner
//            }

            Log.d("TEST","LoginActivity: before NavigationActivity start")
            val intent = Intent(this, NavigationActivity::class.java)
            startActivity(intent)
            Log.d("TEST","LoginActivity: after NavigationActivity start")
        }


    }

}