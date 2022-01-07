package com.example.incorrect_warehouse_app.view.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.core.view.marginLeft
import androidx.core.view.marginRight
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.incorrect_warehouse_app.R
import com.example.incorrect_warehouse_app.model.*
import com.example.incorrect_warehouse_app.view.fragment.AccountantNavFragment
import com.example.incorrect_warehouse_app.view.fragment.AdministratorNavFragment
import com.example.incorrect_warehouse_app.view.fragment.SalesRepNavFragment
import com.example.incorrect_warehouse_app.view.fragment.WarehousemanNavFragment
import com.example.incorrect_warehouse_app.viewModel.DisplayDataViewModel
import kotlinx.android.synthetic.main.activity_display_data.*
import kotlinx.android.synthetic.main.activity_display_data.view.*
import kotlinx.android.synthetic.main.employee_admin_delete_dialog.view.*
import kotlinx.android.synthetic.main.employee_admin_dialog.view.*
import kotlinx.android.synthetic.main.employee_delete_dialog.view.*
import kotlinx.android.synthetic.main.employee_delete_dialog.view.dialogEmpDeleteWindowTitle
import kotlinx.android.synthetic.main.employee_dialog.view.*
import kotlinx.android.synthetic.main.product_delete_dialog.view.*
import kotlinx.android.synthetic.main.product_delete_dialog.view.confirmButton
import kotlinx.android.synthetic.main.product_dialog.view.*
import kotlinx.android.synthetic.main.product_dialog.view.cancelButton
import kotlinx.android.synthetic.main.product_dialog.view.dialogProdNameET
import kotlinx.android.synthetic.main.product_dialog.view.dialogWindowTitle
import kotlinx.android.synthetic.main.product_dialog.view.productSizeText
import kotlinx.android.synthetic.main.product_dialog.view.saveButton
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

//        when (currUser.roleid) {
//            "admin" -> {
//
//            }
//            "warMan" -> {
//
//
//            }
//            "salRep" -> {
//
//            }
//            "acc" -> {
//
//            }
//            else -> null
//        }

        val layoutParamsReserveButton: RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT)
        val layoutParamsAddButton: RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT)
        val layoutParamsModifyButton: RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT)
        val layoutParamsDeleteButton: RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT)

        when(listType){

            "Products" -> {

//                val relativeLayout: RelativeLayout = findViewById(R.id.optionsRecView)
//                val layoutParamsAddButton: RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT)
//                //wszystkie val i setMargin osobno w klamrach
//                layoutParamsAddButton.setMargins(140, 5, 50, 10)
//
//                val layoutParamsEditButton: RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT)
//                layoutParamsEditButton.setMargins(400, 5, 50, 10)

                when (currUser.roleid) {
                    "admin" -> {
//                        reserveButton.isClickable = false
//                        reserveButton.isEnabled = false
//                        reserveButton.isVisible = false
//                        addButton.layoutParams = layoutParamsAddButton
//                        modifyButton.layoutParams = layoutParamsEditButton

                    }
                    "warMan" -> {

                        reserveButton.isClickable = false
                        reserveButton.isEnabled = false
                        reserveButton.isVisible = false

                        addButton.isClickable = false
                        addButton.isEnabled = false
                        addButton.isVisible = false

                        deleteButton.isClickable = false
                        deleteButton.isEnabled = false
                        deleteButton.isVisible = false

                        layoutParamsModifyButton.setMargins(600, 30, 50, 10)
                        modifyButton.layoutParams = layoutParamsModifyButton


                    }
                    "salRep" -> {

                        modifyButton.isClickable = false
                        modifyButton.isEnabled = false
                        modifyButton.isVisible = false

                        addButton.isClickable = false
                        addButton.isEnabled = false
                        addButton.isVisible = false

                        deleteButton.isClickable = false
                        deleteButton.isEnabled = false
                        deleteButton.isVisible = false

                        layoutParamsReserveButton.setMargins(600, 30, 50, 10)
                        reserveButton.layoutParams = layoutParamsModifyButton

                    }
                    "acc" -> {

                        reserveButton.isClickable = false
                        reserveButton.isEnabled = false
                        reserveButton.isVisible = false

                        layoutParamsAddButton.setMargins(250, 5, 50, 10)
                        layoutParamsModifyButton.setMargins(600, 30, 50, 10)
                        layoutParamsDeleteButton.setMargins(950, 30, 50, 10)

                        addButton.layoutParams = layoutParamsAddButton
                        modifyButton.layoutParams = layoutParamsModifyButton
                        deleteButton.layoutParams = layoutParamsDeleteButton

                    }
                    else -> null
                }

                initRetrofitInstanceProducts()

                reserveButton.setOnClickListener {

                }

                addButton.setOnClickListener {
                    //Log.d("TEST addButton:", "dialog window open")

                    val addProductDialogWindow = LayoutInflater.from(this).inflate(R.layout.product_dialog, null)
                    val mBuilder = AlertDialog.Builder(this).setView(addProductDialogWindow)
                    addProductDialogWindow.dialogWindowTitle.text = "New product"
                    val mAlertDialog = mBuilder.show()

                    addProductDialogWindow.saveButton.setOnClickListener{

                        //Log.d("TEST saveButton:","")

                        val prodName = addProductDialogWindow.dialogProdNameET.text.toString()
                        val prodSize = addProductDialogWindow.dialogProdSizeET.text.toString().toInt()
                        val prodAmount = addProductDialogWindow.dialogProdAmountET.text.toString().toInt()
                        val prodPrice = addProductDialogWindow.dialogProdPriceET.text.toString().toFloat()

//                        Log.d("TEST saveButton:", prodName)
//                        Log.d("TEST saveButton:", prodSize.toString())
//                        Log.d("TEST saveButton:", prodAmount.toString())
//                        Log.d("TEST saveButton:", prodPrice.toString())

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
                        modifyProductDialogWindow.dialogWindowTitle.text = "Edit product"
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
                        deleteProductDialogWindow.dialogDeleteWindowTitle.text = "Delete product"
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

                layoutParamsAddButton.setMargins(250, 5, 50, 10)
                layoutParamsModifyButton.setMargins(600, 30, 50, 10)
                layoutParamsDeleteButton.setMargins(950, 30, 50, 10)

                reserveButton.isClickable = false
                reserveButton.isEnabled = false
                reserveButton.isVisible = false
                addButton.layoutParams = layoutParamsAddButton
                modifyButton.layoutParams = layoutParamsModifyButton
                deleteButton.layoutParams = layoutParamsDeleteButton

                when (currUser.roleid) {
                    "admin" -> {

                        initRetrofitInstanceEmployeesAdmin()

                        addButton.setOnClickListener {

                            val addEmployeeAdminDialogWindow = LayoutInflater.from(this).inflate(R.layout.employee_admin_dialog, null)
                            val mBuilder = AlertDialog.Builder(this).setView(addEmployeeAdminDialogWindow)
                            addEmployeeAdminDialogWindow.dialogEmpAdminWindowTitle.text = "New employee"
                            val mAlertDialog = mBuilder.show()

                            addEmployeeAdminDialogWindow.saveButton.setOnClickListener{

                                val empName = addEmployeeAdminDialogWindow.dialogEmpAdminNameET.text.toString()
                                val empSurname = addEmployeeAdminDialogWindow.dialogEmpAdminSurnameET.text.toString()
                                val empSalary = addEmployeeAdminDialogWindow.dialogEmpAdminSalaryET.text.toString().toFloat()
                                val empAddress = addEmployeeAdminDialogWindow.dialogEmpAdminAddressET.text.toString()
                                val empLogin = addEmployeeAdminDialogWindow.dialogEmpAdminLoginET.text.toString()
                                val empEmail = addEmployeeAdminDialogWindow.dialogEmpAdminEmailET.text.toString()
                                val empRole = addEmployeeAdminDialogWindow.dialogEmpAdminRoleET.text.toString()

                                var newEmployeeAdmin = EmployeeAdminData(
                                    0,
                                    empName,
                                    empSurname,
                                    empSalary,
                                    empAddress,
                                    empLogin,
                                    empEmail,
                                    empRole)

                                displayDataViewModel.addNewEmployeeDataAdmin(newEmployeeAdmin){

                                    if(it){
                                        initRetrofitInstanceEmployeesAdmin()
                                    }
                                }

                                mAlertDialog.dismiss()
                            }

                            addEmployeeAdminDialogWindow.cancelButton.setOnClickListener{

                                mAlertDialog.dismiss()
                            }
                        }

                        modifyButton.setOnClickListener {

                            if(selectedItem==null){
                                Toast.makeText(this@DisplayDataActivity, "No employee selected", Toast.LENGTH_SHORT).show()
                            }else {

                                val modifyEmployeeAdminDialogWindow =
                                    LayoutInflater.from(this).inflate(R.layout.employee_admin_dialog, null)
                                val mBuilder = AlertDialog.Builder(this).setView(modifyEmployeeAdminDialogWindow)
                                modifyEmployeeAdminDialogWindow.dialogEmpAdminWindowTitle.text = "Edit employee"
                                val mAlertDialog = mBuilder.show()

                                var employeesAdminList: List<EmployeeAdminData>? = null
                                displayDataViewModel.employeeAdminList.observe(this, {
                                    employeesAdminList = it
                                })

                                var selectedEmployeeId: Int? = null

                                selectedItem?.let { it1 ->
                                    modifyEmployeeAdminDialogWindow.dialogEmpAdminNameET.setText(employeesAdminList?.get(it1)?.name.toString())
                                    modifyEmployeeAdminDialogWindow.dialogEmpAdminSurnameET.setText(employeesAdminList?.get(it1)?.surname.toString())
                                    modifyEmployeeAdminDialogWindow.dialogEmpAdminSalaryET.setText(employeesAdminList?.get(it1)?.salary.toString())
                                    modifyEmployeeAdminDialogWindow.dialogEmpAdminAddressET.setText(employeesAdminList?.get(it1)?.address.toString())
                                    modifyEmployeeAdminDialogWindow.dialogEmpAdminLoginET.setText(employeesAdminList?.get(it1)?.login.toString())
                                    modifyEmployeeAdminDialogWindow.dialogEmpAdminEmailET.setText(employeesAdminList?.get(it1)?.email.toString())
                                    modifyEmployeeAdminDialogWindow.dialogEmpAdminRoleET.setText(employeesAdminList?.get(it1)?.rolename.toString())

                                    selectedEmployeeId = employeesAdminList?.get(it1)?.employeeid
                                }

                                modifyEmployeeAdminDialogWindow.saveButton.setOnClickListener {

                                    var employee = selectedEmployeeId?.let { it1 ->
                                        EmployeeAdminData(
                                            it1,
                                            modifyEmployeeAdminDialogWindow.dialogEmpNameET.text.toString(),
                                            modifyEmployeeAdminDialogWindow.dialogEmpSurnameET.text.toString(),
                                            modifyEmployeeAdminDialogWindow.dialogEmpSalaryET.text.toString().toFloat(),
                                            modifyEmployeeAdminDialogWindow.dialogEmpAddressET.text.toString(),
                                            modifyEmployeeAdminDialogWindow.dialogEmpAdminLoginET.text.toString(),
                                            modifyEmployeeAdminDialogWindow.dialogEmpAdminEmailET.text.toString(),
                                            modifyEmployeeAdminDialogWindow.dialogEmpAdminRoleET.text.toString()
                                        )
                                    }

                                    if (employee != null) {
                                        displayDataViewModel.modifyEmployeeAdmin(employee){

                                            if(it){
                                                initRetrofitInstanceEmployeesAdmin()
                                            }
                                        }
                                    }

                                    mAlertDialog.dismiss()
                                }

                                modifyEmployeeAdminDialogWindow.cancelButton.setOnClickListener {

                                    mAlertDialog.dismiss()
                                }
                            }
                        }

                        deleteButton.setOnClickListener {

                            if(selectedItem==null){
                                Toast.makeText(this@DisplayDataActivity, "No employee selected", Toast.LENGTH_SHORT).show()
                            }else {

                                val deleteEmployeeAdminDialogWindow = LayoutInflater.from(this).inflate(R.layout.employee_admin_delete_dialog, null)
                                val mBuilder = AlertDialog.Builder(this).setView(deleteEmployeeAdminDialogWindow)
                                deleteEmployeeAdminDialogWindow.dialogDeleteEmpAdminWindowTitle.text = "Delete employee"
                                val mAlertDialog = mBuilder.show()

                                var employeesAdminList: List<EmployeeAdminData>? = null
                                displayDataViewModel.employeeAdminList.observe(this, {
                                    employeesAdminList = it
                                })

                                var selectedEmployeeId: Int? = null
                                var empName: String? = null
                                var empSurname: String? = null
                                var empSalary: Float? = null
                                var empAddress: String? = null
                                var empLogin: String? = null
                                var empEmail: String? = null
                                var empRole: String? = null

                                selectedItem?.let { it1 ->

                                    empName = employeesAdminList?.get(it1)?.name.toString()
                                    empSurname = employeesAdminList?.get(it1)?.surname.toString()
                                    empSalary = employeesAdminList?.get(it1)?.salary.toString().toFloat()
                                    empAddress = employeesAdminList?.get(it1)?.address.toString()
                                    empLogin = employeesAdminList?.get(it1)?.login.toString()
                                    empEmail = employeesAdminList?.get(it1)?.email.toString()
                                    empRole = employeesAdminList?.get(it1)?.rolename.toString()
                                    selectedEmployeeId = employeesAdminList?.get(it1)?.employeeid.toString().toInt()
                                }

                                deleteEmployeeAdminDialogWindow.dialogDeleteEmpAdminName.text = empName
                                deleteEmployeeAdminDialogWindow.dialogDeleteEmpAdminSurname.text = empSurname
                                deleteEmployeeAdminDialogWindow.dialogDeleteEmpAdminSalary.text = empSalary.toString()
                                deleteEmployeeAdminDialogWindow.dialogDeleteEmpAdminAddress.text = empAddress
                                deleteEmployeeAdminDialogWindow.dialogDeleteEmpAdminLogin.text = empLogin
                                deleteEmployeeAdminDialogWindow.dialogDeleteEmpAdminEmail.text = empEmail
                                deleteEmployeeAdminDialogWindow.dialogDeleteEmpAdminRole.text = empRole


                                deleteEmployeeAdminDialogWindow.confirmButton.setOnClickListener {

                                    if(selectedEmployeeId!=null) {
                                        displayDataViewModel.deleteEmployeeAdmin(selectedEmployeeId!!){
                                            if(it){
                                                initRetrofitInstanceEmployeesAdmin()
                                                selectedEmployeeId = null
                                            }
                                        }
                                    }

                                    mAlertDialog.dismiss()
                                }

                                deleteEmployeeAdminDialogWindow.cancelButton.setOnClickListener {

                                    mAlertDialog.dismiss()
                                }
                            }
                        }

                        refreshButton.setOnClickListener {
                            initRetrofitInstanceEmployeesAdmin()
                        }

                    }
                    "acc" -> {

                        initRetrofitInstanceEmployees()

                        addButton.setOnClickListener {

                            val addEmployeeDialogWindow = LayoutInflater.from(this).inflate(R.layout.employee_dialog, null)
                            val mBuilder = AlertDialog.Builder(this).setView(addEmployeeDialogWindow)
                            addEmployeeDialogWindow.dialogWindowTitle.text = "New employee"
                            val mAlertDialog = mBuilder.show()

                            addEmployeeDialogWindow.saveButton.setOnClickListener{

                                val empName = addEmployeeDialogWindow.dialogEmpNameET.text.toString()
                                val empSurname = addEmployeeDialogWindow.dialogEmpSurnameET.text.toString()
                                val empSalary = addEmployeeDialogWindow.dialogEmpSalaryET.text.toString().toFloat()
                                val empAddress = addEmployeeDialogWindow.dialogEmpAddressET.text.toString()

                                var newEmployee = Employee(0,empName,empSurname,empSalary,empAddress)

                                displayDataViewModel.addNewEmployeeData(newEmployee){

                                    if(it){
                                        initRetrofitInstanceEmployees()
                                    }
                                }

                                mAlertDialog.dismiss()
                            }

                            addEmployeeDialogWindow.cancelButton.setOnClickListener{

                                Log.d("TEST cancelButton:","")

                                mAlertDialog.dismiss()
                            }
                        }

                        modifyButton.setOnClickListener {

                            if(selectedItem==null){
                                Toast.makeText(this@DisplayDataActivity, "No employee selected", Toast.LENGTH_SHORT).show()
                            }else {

                                val modifyEmployeeDialogWindow =
                                    LayoutInflater.from(this).inflate(R.layout.employee_dialog, null)
                                val mBuilder = AlertDialog.Builder(this).setView(modifyEmployeeDialogWindow)
                                modifyEmployeeDialogWindow.dialogWindowTitle.text = "Edit employee"
                                val mAlertDialog = mBuilder.show()

                                var employeesList: List<Employee>? = null
                                displayDataViewModel.employeeList.observe(this, {
                                    employeesList = it
                                })

                                var selectedEmployeeId: Int? = null

                                selectedItem?.let { it1 ->
                                    modifyEmployeeDialogWindow.dialogEmpNameET.setText(employeesList?.get(it1)?.name.toString())
                                    modifyEmployeeDialogWindow.dialogEmpSurnameET.setText(employeesList?.get(it1)?.surname.toString())
                                    modifyEmployeeDialogWindow.dialogEmpSalaryET.setText(employeesList?.get(it1)?.salary.toString())
                                    modifyEmployeeDialogWindow.dialogEmpAddressET.setText(employeesList?.get(it1)?.address.toString())

                                    selectedEmployeeId = employeesList?.get(it1)?.employeeid
                                }

                                modifyEmployeeDialogWindow.saveButton.setOnClickListener {

                                    var employee = selectedEmployeeId?.let { it1 ->
                                        Employee(
                                            it1,
                                            modifyEmployeeDialogWindow.dialogEmpNameET.text.toString(),
                                            modifyEmployeeDialogWindow.dialogEmpSurnameET.text.toString(),
                                            modifyEmployeeDialogWindow.dialogEmpSalaryET.text.toString().toFloat(),
                                            modifyEmployeeDialogWindow.dialogEmpAddressET.text.toString()
                                        )
                                    }

                                    if (employee != null) {
                                        displayDataViewModel.modifyEmployee(employee){

                                            if(it){
                                                initRetrofitInstanceEmployees()
                                            }
                                        }
                                    }

                                    mAlertDialog.dismiss()
                                }

                                modifyEmployeeDialogWindow.cancelButton.setOnClickListener {

                                    mAlertDialog.dismiss()
                                }
                            }
                        }

                        deleteButton.setOnClickListener {

                            if(selectedItem==null){
                                Toast.makeText(this@DisplayDataActivity, "No employee selected", Toast.LENGTH_SHORT).show()
                            }else {

                                val deleteEmployeeDialogWindow = LayoutInflater.from(this).inflate(R.layout.employee_delete_dialog, null)
                                val mBuilder = AlertDialog.Builder(this).setView(deleteEmployeeDialogWindow)
                                deleteEmployeeDialogWindow.dialogEmpDeleteWindowTitle.text = "Delete employee"
                                val mAlertDialog = mBuilder.show()

                                var employeesList: List<Employee>? = null
                                displayDataViewModel.employeeList.observe(this, {
                                    employeesList = it
                                })

                                var selectedEmployeeId: Int? = null
                                var empName: String? = null
                                var empSurname: String? = null
                                var empSalary: Float? = null
                                var empAddress: String? = null

                                selectedItem?.let { it1 ->

                                    empName = employeesList?.get(it1)?.name.toString()
                                    empSurname = employeesList?.get(it1)?.surname.toString()
                                    empSalary = employeesList?.get(it1)?.salary.toString().toFloat()
                                    empAddress = employeesList?.get(it1)?.address.toString()
                                    selectedEmployeeId = employeesList?.get(it1)?.employeeid.toString().toInt()
                                }

                                deleteEmployeeDialogWindow.dialogDeleteEmpName.text = empName
                                deleteEmployeeDialogWindow.dialogDeleteEmpSurname.text = empSurname
                                deleteEmployeeDialogWindow.dialogDeleteEmpSalary.text = empSalary.toString()
                                deleteEmployeeDialogWindow.dialogDeleteEmpAddress.text = empAddress

                                deleteEmployeeDialogWindow.confirmButton.setOnClickListener {

                                    if(selectedEmployeeId!=null) {
                                        displayDataViewModel.deleteEmployee(selectedEmployeeId!!){
                                            if(it){
                                                initRetrofitInstanceEmployees()
                                                selectedEmployeeId = null
                                            }
                                        }
                                    }

                                    mAlertDialog.dismiss()
                                }

                                deleteEmployeeDialogWindow.cancelButton.setOnClickListener {

                                    mAlertDialog.dismiss()
                                }
                            }
                        }

                        refreshButton.setOnClickListener {
                            initRetrofitInstanceEmployees()
                        }
                    }
                    else -> null
                }
            }
            "Reservations" -> {

                layoutParamsAddButton.setMargins(250, 5, 50, 10)
                layoutParamsModifyButton.setMargins(600, 30, 50, 10)
                layoutParamsDeleteButton.setMargins(950, 30, 50, 10)

                reserveButton.isClickable = false
                reserveButton.isEnabled = false
                reserveButton.isVisible = false
                addButton.layoutParams = layoutParamsAddButton
                modifyButton.layoutParams = layoutParamsModifyButton
                deleteButton.layoutParams = layoutParamsDeleteButton

                initRetrofitInstanceReservations()


                refreshButton.setOnClickListener {
                    initRetrofitInstanceReservations()
                }

            }
            "Low stock" ->{

                layoutParamsAddButton.setMargins(250, 5, 50, 10)
                layoutParamsModifyButton.setMargins(600, 30, 50, 10)
                layoutParamsDeleteButton.setMargins(950, 30, 50, 10)

                reserveButton.isClickable = false
                reserveButton.isEnabled = false
                reserveButton.isVisible = false
                addButton.layoutParams = layoutParamsAddButton
                modifyButton.layoutParams = layoutParamsModifyButton
                deleteButton.layoutParams = layoutParamsDeleteButton



            }
            "New employees" ->{

                layoutParamsAddButton.setMargins(250, 5, 50, 10)
                layoutParamsModifyButton.setMargins(600, 30, 50, 10)
                layoutParamsDeleteButton.setMargins(950, 30, 50, 10)

                reserveButton.isClickable = false
                reserveButton.isEnabled = false
                reserveButton.isVisible = false
                addButton.layoutParams = layoutParamsAddButton
                modifyButton.layoutParams = layoutParamsModifyButton
                deleteButton.layoutParams = layoutParamsDeleteButton


            }
        }



    }

    private fun initRetrofitInstanceProducts(){
        displayDataViewModel = ViewModelProvider(this).get(DisplayDataViewModel::class.java)
        displayDataViewModel.getProductsData()
        //observer
        displayDataViewModel.productList.observe(this,{
            initAdapterProducts(it)
        })
    }

    private fun initAdapterProducts(productsList: List<Product>){
        recViewDisplayData.layoutManager = LinearLayoutManager(this)
        val adapter = ProductAdapter(productsList)
        recViewDisplayData.adapter = adapter
        adapter.setOnItemClickListener(object : ProductAdapter.onItemClickListner{
            override fun onItemClick(position: Int) {

                selectedItem = position

                //Toast.makeText(this@DisplayDataActivity, "Selected item: $position", Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun initRetrofitInstanceReservations(){
        displayDataViewModel = ViewModelProvider(this).get(DisplayDataViewModel::class.java)
        displayDataViewModel.getReservationsData()
        //observer
        displayDataViewModel.reservationList.observe(this,{
            initAdapterReservations(it)
        })
    }

    private fun initAdapterReservations(reservationsList: List<Reservation>){
        recViewDisplayData.layoutManager = LinearLayoutManager(this)
        val adapter = ReservationAdapter(reservationsList)
        recViewDisplayData.adapter = adapter
        adapter.setOnItemClickListener(object : ReservationAdapter.onItemClickListner{
            override fun onItemClick(position: Int) {

                selectedItem = position
            }
        })
    }

    private fun initRetrofitInstanceEmployees(){
        displayDataViewModel = ViewModelProvider(this).get(DisplayDataViewModel::class.java)
        displayDataViewModel.getEmployeeData()
        //observer
        displayDataViewModel.employeeList.observe(this,{
            initAdapterEmployees(it)
        })
    }

    private fun initAdapterEmployees(employeesList: List<Employee>){
        recViewDisplayData.layoutManager = LinearLayoutManager(this)
        val adapter = EmployeeAdapter(employeesList)
        recViewDisplayData.adapter = adapter
        adapter.setOnItemClickListener(object : EmployeeAdapter.onItemClickListner{
            override fun onItemClick(position: Int) {

                selectedItem = position
            }
        })
    }

    private fun initRetrofitInstanceEmployeesAdmin(){
        displayDataViewModel = ViewModelProvider(this).get(DisplayDataViewModel::class.java)
        displayDataViewModel.getEmployeeDataAdmin()
        //observer
        displayDataViewModel.employeeAdminList.observe(this,{
            initAdapterEmployeesAdmin(it)
        })
    }

    private fun initAdapterEmployeesAdmin(employeesAdminList: List<EmployeeAdminData>){
        recViewDisplayData.layoutManager = LinearLayoutManager(this)
        val adapter = EmployeeAdminAdapter(employeesAdminList)
        recViewDisplayData.adapter = adapter
        adapter.setOnItemClickListener(object : EmployeeAdminAdapter.onItemClickListner{
            override fun onItemClick(position: Int) {

                selectedItem = position
            }
        })
    }
}