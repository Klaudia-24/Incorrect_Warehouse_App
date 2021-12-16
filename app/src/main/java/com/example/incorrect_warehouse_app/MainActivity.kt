package com.example.incorrect_warehouse_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.incorrect_warehouse_app.model.PostgreSqlDatabase

class MainActivity : AppCompatActivity() {
    companion object {
        val db = PostgreSqlDatabase()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("TEST","Proba polaczenia")
        db.dbConn("try")
        Log.d("TEST","Proba zakonczona")

    }
}