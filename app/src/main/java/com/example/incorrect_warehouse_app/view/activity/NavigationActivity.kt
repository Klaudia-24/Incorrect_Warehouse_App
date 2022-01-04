package com.example.incorrect_warehouse_app.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.incorrect_warehouse_app.R
import kotlinx.android.synthetic.main.activity_navigation.*


class NavigationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)

        Log.d("TEST","NavigationActivity start")

        productsButton.setOnClickListener{

            Log.d("TEST","productsButton: before DisplayDataActivity start")
            val intent = Intent(this, DisplayDataActivity::class.java)
            startActivity(intent)
            Log.d("TEST","productsButton: after DisplayDataActivity start")
        }
    }
}