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
import kotlinx.android.synthetic.main.activity_display_data.view.*
import kotlinx.android.synthetic.main.product_delete_dialog.view.*
import kotlinx.android.synthetic.main.product_dialog.view.*
import kotlinx.android.synthetic.main.product_dialog.view.cancelButton
import kotlinx.android.synthetic.main.product_dialog.view.dialogProdNameET
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



        backToNavButton.setOnClickListener {
            Intent(this, NavigationActivity::class.java).also {
                it.putExtra("EXTRA_CURRENT_USER", currUser)
                startActivity(it)
            }
        }


        when(listType){
            "Products" -> {

                initRetrofitInstanceProducts()

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

                            var product = selectedProductId?.let { it1 ->
                                Product(
                                    it1,
                                    modifyProductDialogWindow.dialogProdNameET.text.toString(),
                                    modifyProductDialogWindow.dialogProdSizeET.text.toString().toInt(),
                                    modifyProductDialogWindow.dialogProdAmountET.text.toString().toInt(),
                                    modifyProductDialogWindow.dialogProdPriceET.text.toString().toFloat()
                                )
                            }

                            if (product != null) {
                                displayDataViewModel.modifyProduct(product){

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

                    Log.d("TEST deleteButton:", "dialog window open")

                    if(selectedItem==null){
                        Toast.makeText(this@DisplayDataActivity, "No product selected", Toast.LENGTH_SHORT).show()
                    }else {

                        val deleteProductDialogWindow = LayoutInflater.from(this).inflate(R.layout.product_delete_dialog, null)
                        val mBuilder = AlertDialog.Builder(this).setView(deleteProductDialogWindow)
                        deleteProductDialogWindow.dialogDeleteWindowTitle.text = "Delete Product"
                        val mAlertDialog = mBuilder.show()

                        Log.d("TEST deleteButton:", "after initialization")

                        var productsList: List<Product>? = null
                        displayDataViewModel.productList.observe(this, {
                            productsList = it
                        })

                        Log.d("TEST deleteButton:", "product list get")

                        var selectedProductId: Int? = null
                        var prodName: String? = null
                        var prodSize: String? = null
                        var prodAmount: String? = null
                        var prodPrice: String? = null

                        selectedItem?.let { it1 ->
//                    deleteProductDialogWindow.dialogDeleteProdName.setText(productsList?.get(it1)?.name.toString())
//                    deleteProductDialogWindow.dialogDeleteProdSize.setText(productsList?.get(it1)?.sizeofproduct.toString())
//                    deleteProductDialogWindow.dialogDeleteProdAmount.setText(productsList?.get(it1)?.amount.toString())
//                    deleteProductDialogWindow.dialogDeleteProdPrice.setText(productsList?.get(it1)?.price.toString())

                            prodName = productsList?.get(it1)?.name.toString()
                            prodSize = productsList?.get(it1)?.sizeofproduct.toString()
                            prodAmount = productsList?.get(it1)?.amount.toString()
                            prodPrice = productsList?.get(it1)?.price.toString()
                            selectedProductId = productsList?.get(it1)?.productid.toString().toInt()
                        }

                        deleteProductDialogWindow.dialogDeleteProdName.text = prodName
                        deleteProductDialogWindow.dialogDeleteProdSize.text = prodSize
                        deleteProductDialogWindow.dialogDeleteProdAmount.text = prodAmount
                        deleteProductDialogWindow.dialogDeleteProdPrice.text = prodPrice


                        Log.d("TEST deleteButton:", "after setting texts")

                        deleteProductDialogWindow.confirmButton.setOnClickListener {

                            Log.d("TEST selectedProductId:", selectedProductId.toString())

                            if(selectedProductId!=null) {
                                displayDataViewModel.deleteProduct(selectedProductId!!){
                                    Log.d("TEST status:", it.toString())
                                    if(it){
                                        initRetrofitInstanceProducts()
                                        selectedProductId = null
                                    }
                                }
                            }

                            mAlertDialog.dismiss()
                        }

                        deleteProductDialogWindow.cancelButton.setOnClickListener {

                            mAlertDialog.dismiss()
                        }
                    }
                }

                refreshButton.setOnClickListener {
                    initRetrofitInstanceProducts()
                }

            }
            "Employees" -> {

            }
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