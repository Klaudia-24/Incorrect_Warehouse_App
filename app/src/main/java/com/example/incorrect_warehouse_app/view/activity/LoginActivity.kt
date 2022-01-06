package com.example.incorrect_warehouse_app.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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

            //Log.d("TEST","before loginViewModel")

            loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
            //Log.d("TEST","before signInUser method")
            //loginViewModel.signInUser(userLogin, password)

            loginViewModel.signInUser("CHBaker", "user1"){
                //Log.d("TEST","before if")
                //Log.d("TEST signInStatus: ", it.toString())

                if(it){
                    loginViewModel.getCurrentUser("CHBaker"){
                        //Log.d("TEST","LoginActivity if")
                        //Log.d("TEST current user: ", it.toString())

                        val currUser = it

                        //Log.d("TEST","LoginActivity: before NavigationActivity start")
                        Intent(this, NavigationActivity::class.java).also {
                            Log.d("TEST LoginActivity:",currUser?.roleid.toString())
                            it.putExtra("EXTRA_CURRENT_USER", currUser)
                            startActivity(it)
                        }

                        //Log.d("TEST","LoginActivity: after NavigationActivity start")
                    }
                }
            }
        }
    }
}
