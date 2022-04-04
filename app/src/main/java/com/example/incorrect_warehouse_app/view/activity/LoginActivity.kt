package com.example.incorrect_warehouse_app.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.incorrect_warehouse_app.R
import com.example.incorrect_warehouse_app.model.*
import com.example.incorrect_warehouse_app.viewModel.LoginViewModel
import kotlinx.android.synthetic.main.activity_display_data.*
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        signInButton.setOnClickListener setOnClickListner@{

            val userLogin = userLoginEditText.text.toString()
            val password = passwordEditText.text.toString()

            if(userLogin.isEmpty()){
                userLoginEditText.error = "Enter Your user login"
                userLoginEditText.requestFocus()
                return@setOnClickListner
            }
            if(password.isEmpty()){
                passwordEditText.error = "Enter Your password"
                passwordEditText.requestFocus()
                return@setOnClickListner
            }

            loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

            loginViewModel.signInUser(userLogin, password){

                if(it){
                    loginViewModel.getCurrentUser(userLogin){

                        val currUser = it

                        Intent(this, NavigationActivity::class.java).also {
                            it.putExtra("EXTRA_CURRENT_USER", currUser)
                            startActivity(it)
                        }
                    }
                }
                else{
                    Toast.makeText(
                        this@LoginActivity,
                        "Cannot connect to the server",
                        android.widget.Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}


