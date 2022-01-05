package com.example.incorrect_warehouse_app.view.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.incorrect_warehouse_app.R
import com.example.incorrect_warehouse_app.model.CurrentUser
import com.example.incorrect_warehouse_app.model.Product
import com.example.incorrect_warehouse_app.model.ProductAdapter
import com.example.incorrect_warehouse_app.viewModel.DisplayDataViewModel
import com.example.incorrect_warehouse_app.viewModel.LoginViewModel
import kotlinx.android.synthetic.main.activity_display_data.*

class DisplayDataActivity : AppCompatActivity() {

    private lateinit var displayDataViewModel: DisplayDataViewModel
    var selectedItem: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_data)
        //Log.d("TEST","DisplayDataActivity: DisplayDataActivity start")

        val currUser = intent.getSerializableExtra("EXTRA_CURRENT_USER") as CurrentUser
        val listType = intent.getSerializableExtra("EXTRA_LIST_TYPE") as String

//        Log.d("TEST DisplayDataAct:",currUser?.toString())
//        Log.d("TEST DisplayDataAct:", listType)

        initRetrofitInstanceProducts()

        backToNavButton.setOnClickListener {
            Intent(this, NavigationActivity::class.java).also {
                it.putExtra("EXTRA_CURRENT_USER", currUser)
                startActivity(it)
            }
        }

        addButton.setOnClickListener {
            Log.d("TEST addButton:", selectedItem.toString())
        }
    }

    private fun initRetrofitInstanceProducts(){
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
        adapter.setOnItemClickListener(object : ProductAdapter.onItemClickListner{
            override fun onItemClick(position: Int) {

                //var itemPosition = position+1
                selectedItem = position

                Toast.makeText(this@DisplayDataActivity, "Selected item: $position", Toast.LENGTH_SHORT).show()
            }

        })
    }
}