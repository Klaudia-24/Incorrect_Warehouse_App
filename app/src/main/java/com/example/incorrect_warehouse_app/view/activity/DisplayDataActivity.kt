package com.example.incorrect_warehouse_app.view.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.incorrect_warehouse_app.R
import com.example.incorrect_warehouse_app.model.CurrentUser
import com.example.incorrect_warehouse_app.model.Product
import com.example.incorrect_warehouse_app.model.ProductAdapter
import com.example.incorrect_warehouse_app.viewModel.DisplayDataViewModel
import kotlinx.android.synthetic.main.activity_display_data.*
import kotlinx.android.synthetic.main.product_dialog.view.*
import kotlinx.android.synthetic.main.product_dialog.view.productSizeText
import kotlinx.android.synthetic.main.product_list.view.*

class DisplayDataActivity : AppCompatActivity() {

    private lateinit var displayDataViewModel: DisplayDataViewModel
    var selectedItem: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_data)
        //Log.d("TEST","DisplayDataActivity: DisplayDataActivity start")

        val currUser = intent.getSerializableExtra("EXTRA_CURRENT_USER") as CurrentUser
        val listType = intent.getSerializableExtra("EXTRA_LIST_TYPE") as String

        //(R.id.dataTitle)
        dataTitle.setText(listType)

//        Log.d("TEST DisplayDataAct:",currUser?.toString())
//        Log.d("TEST DisplayDataAct:", listType)

        initRetrofitInstanceProducts()

        backToNavButton.setOnClickListener {
            Intent(this, NavigationActivity::class.java).also {
                it.putExtra("EXTRA_CURRENT_USER", currUser)
                startActivity(it)
            }
        }

        reserveButton.setOnClickListener {

        }

        addButton.setOnClickListener {
            Log.d("TEST addButton:", "dialog window open")

            val addProductDialogWindow = LayoutInflater.from(this).inflate(R.layout.product_dialog, null)
            val mBuilder = AlertDialog.Builder(this).setView(addProductDialogWindow)
            addProductDialogWindow.dialogWindowTitle.text = "New Product"
            val mAlertDialog = mBuilder.show()

            addProductDialogWindow.saveButton.setOnClickListener{

                Log.d("TEST saveButton:","")

                val prodName = addProductDialogWindow.dialogProdNameET.text.toString()
                val prodSize = addProductDialogWindow.dialogProdSizeET.text.toString().toInt()
                val prodAmount = addProductDialogWindow.dialogProdAmountET.text.toString().toInt()
                val prodPrice = addProductDialogWindow.dialogProdPriceET.text.toString().toFloat()

                Log.d("TEST saveButton:", prodName)
                Log.d("TEST saveButton:", prodSize.toString())
                Log.d("TEST saveButton:", prodAmount.toString())
                Log.d("TEST saveButton:", prodPrice.toString())

                var newProduct = Product(0, prodName, prodSize, prodAmount, prodPrice)

                displayDataViewModel.addNewProductData(newProduct){

                    if(it){
                        initRetrofitInstanceProducts()
                    }
                }

                mAlertDialog.dismiss()
            }

            addProductDialogWindow.cancelButton.setOnClickListener{

                Log.d("TEST cancelButton:","")

                mAlertDialog.dismiss()
            }

            Log.d("TEST addButton:", selectedItem.toString())


        }

        modifyButton.setOnClickListener {

            Log.d("TEST modifyButton:", "dialog window open")

            if(selectedItem==null){
                Toast.makeText(this@DisplayDataActivity, "No product selected", Toast.LENGTH_SHORT).show()
            }else {

                val modifyProductDialogWindow =
                    LayoutInflater.from(this).inflate(R.layout.product_dialog, null)
                val mBuilder = AlertDialog.Builder(this).setView(modifyProductDialogWindow)
                modifyProductDialogWindow.dialogWindowTitle.text = "Edit Product"
                val mAlertDialog = mBuilder.show()

                var productsList: List<Product>? = null
                displayDataViewModel.productList.observe(this, {
                    productsList = it
                })

                var selectedProductId: Int? = null

                selectedItem?.let { it1 ->
                    modifyProductDialogWindow.dialogProdNameET.setText(productsList?.get(it1)?.name.toString())
                    modifyProductDialogWindow.dialogProdSizeET.setText(productsList?.get(it1)?.sizeofproduct.toString())
                    modifyProductDialogWindow.dialogProdAmountET.setText(productsList?.get(it1)?.amount.toString())
                    modifyProductDialogWindow.dialogProdPriceET.setText(productsList?.get(it1)?.price.toString())

                    selectedProductId = productsList?.get(it1)?.productid
                }

                modifyProductDialogWindow.saveButton.setOnClickListener {

                    var newProduct = selectedProductId?.let { it1 ->
                        Product(
                            it1,
                            modifyProductDialogWindow.dialogProdNameET.text.toString(),
                            modifyProductDialogWindow.dialogProdSizeET.text.toString().toInt(),
                            modifyProductDialogWindow.dialogProdAmountET.text.toString().toInt(),
                            modifyProductDialogWindow.dialogProdPriceET.text.toString().toFloat()
                        )
                    }

                    if (newProduct != null) {
                        displayDataViewModel.modifyProduct(newProduct){

                            if(it){
                                initRetrofitInstanceProducts()
                            }
                        }
                    }

                    mAlertDialog.dismiss()
                }

                modifyProductDialogWindow.cancelButton.setOnClickListener {

                    mAlertDialog.dismiss()
                }
            }
        }

        deleteButton.setOnClickListener {


        }

        refreshButton.setOnClickListener {
            initRetrofitInstanceProducts()
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

                //Toast.makeText(this@DisplayDataActivity, "Selected item: $position", Toast.LENGTH_SHORT).show()
            }

        })
    }
}