package com.example.incorrect_warehouse_app.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.incorrect_warehouse_app.R
import com.example.incorrect_warehouse_app.model.PostgreSqlDatabase

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

    }
}