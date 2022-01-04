package com.example.incorrect_warehouse_app.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.incorrect_warehouse_app.R
import com.example.incorrect_warehouse_app.model.PostgreSqlDatabase
import com.example.incorrect_warehouse_app.model.Product
import com.example.incorrect_warehouse_app.model.ProductAdapter
import com.example.incorrect_warehouse_app.model.RetrofitService
import com.example.incorrect_warehouse_app.viewModel.DisplayDataViewModel
import com.example.incorrect_warehouse_app.viewModel.LoginViewModel
import kotlinx.android.synthetic.main.activity_display_data.*
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginActivity : AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        signInButton.setOnClickListener setOnClickListner@{

            val userLogin = userLoginEditText.text.toString()
            val password = passwordEditText.text.toString()

//            if(userLogin.isEmpty()){
//                Log.d("TEST","user login empty")
//                userLoginEditText.error = "Enter Your user login"
//                userLoginEditText.requestFocus()
//                return@setOnClickListner
//            }
//            if(password.isEmpty()){
//                passwordEditText.error = "Enter Your password"
//                passwordEditText.requestFocus()
//                return@setOnClickListner
//            }

            Log.d("TEST","before loginViewModel")

            loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
            Log.d("TEST","before signInUser method")
            //loginViewModel.signInUser(userLogin, password)

            loginViewModel.signInUser("SWilson", "user2"){
                Log.d("TEST","before if")
                Log.d("TEST signInStatus: ", it.toString())

                if(it){
                    loginViewModel.getCurrentUser("SWilson"){
                        Log.d("TEST","LoginActivity if")
                        Log.d("TEST current user: ", it.toString())

                        Log.d("TEST","LoginActivity: before NavigationActivity start")
                        val intent = Intent(this, NavigationActivity::class.java)
                        startActivity(intent)
                        Log.d("TEST","LoginActivity: after NavigationActivity start")
                    }

                }
            }




        }
    }
}

//private fun initRetrofitInstance(){
//    displayDataViewModel = ViewModelProvider(this).get(DisplayDataViewModel::class.java)
//    displayDataViewModel.getProductsData()
//    //observer
//    displayDataViewModel.productList.observe(this,{
//        initAdapter(it)
//    })
//}
//
//private fun initAdapter(productsList: List<Product>){
//    recViewDisplayData.layoutManager = LinearLayoutManager(this)
//    val adapter = ProductAdapter(productsList)
//    recViewDisplayData.adapter = adapter
//}