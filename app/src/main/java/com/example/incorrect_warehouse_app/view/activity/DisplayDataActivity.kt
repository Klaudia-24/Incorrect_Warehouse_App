package com.example.incorrect_warehouse_app.view.activity

import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.incorrect_warehouse_app.R
import com.example.incorrect_warehouse_app.model.Product
import com.example.incorrect_warehouse_app.model.ProductAdapter
import com.example.incorrect_warehouse_app.viewModel.DisplayDataViewModel
import com.example.incorrect_warehouse_app.viewModel.LoginViewModel
import kotlinx.android.synthetic.main.activity_display_data.*

class DisplayDataActivity : AppCompatActivity() {

    private lateinit var displayDataViewModel: DisplayDataViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_data)
        Log.d("TEST","DisplayDataActivity: DisplayDataActivity start")
        initRetrofitInstance()
    }

    private fun initRetrofitInstance(){
        displayDataViewModel = ViewModelProvider(this).get(DisplayDataViewModel::class.java)
        displayDataViewModel.getProductsData()
        //observer
        displayDataViewModel.productList.observe(this,{
            initAdapter(it)
        })
    }

    private fun initAdapter(productsList: List<Product>){
        recViewDisplayData.layoutManager = LinearLayoutManager(this)
        val adapter = ProductAdapter(productsList)
        recViewDisplayData.adapter = adapter
    }
}