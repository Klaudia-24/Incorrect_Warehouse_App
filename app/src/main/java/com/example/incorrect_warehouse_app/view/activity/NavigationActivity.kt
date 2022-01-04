package com.example.incorrect_warehouse_app.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.incorrect_warehouse_app.R
import com.example.incorrect_warehouse_app.model.CurrentUser
import kotlinx.android.synthetic.main.activity_navigation.*


class NavigationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)

        //Log.d("TEST","NavigationActivity start")

        productsButton.setOnClickListener{

            //Log.d("TEST","productsButton: before DisplayDataActivity start")
            val intent = Intent(this, DisplayDataActivity::class.java)
            startActivity(intent)
            //Log.d("TEST","productsButton: after DisplayDataActivity start")
        }

        //val surname = intent.getStringExtra("EXTRA_SURNAME")

        val currUser = intent.getSerializableExtra("EXTRA_CURRENT_USER") as CurrentUser

        myProfileButtonNav.setOnClickListener{
            Log.d("TEST NavActivity:", "onClick")
            Intent(this, ProfileActivity::class.java).also {
                it.putExtra("EXTRA_CURRENT_USER", currUser)
                startActivity(it)
            }

        }
    }
}