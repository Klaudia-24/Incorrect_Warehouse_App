package com.example.incorrect_warehouse_app.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.incorrect_warehouse_app.R
import com.example.incorrect_warehouse_app.model.PostgreSqlDatabase
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    companion object {
        val db = PostgreSqlDatabase()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        Log.d("TEST","Proba polaczenia")
        db.dbConn("try")
        Log.d("TEST","Proba zakonczona")

        signInButton.setOnClickListener setOnClickListner@{

            val userLogin = userLoginEditText.text.toString()
            val password = passwordEditText.text.toString()

            if(userLogin.isEmpty()){
                userLoginEditText.error = "Enter Your user name"
                userLoginEditText.requestFocus()
                return@setOnClickListner
            }

            if(password.isEmpty()){
                passwordEditText.error = "Enter Your password"
                passwordEditText.requestFocus()
                return@setOnClickListner
            }

            //val intent = Intent(this, NavigationActivity::class.java)
            //startActivity(intent)
        }


    }
}