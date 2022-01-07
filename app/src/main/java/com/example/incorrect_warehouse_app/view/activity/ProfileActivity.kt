package com.example.incorrect_warehouse_app.view.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.incorrect_warehouse_app.R
import com.example.incorrect_warehouse_app.model.CurrentUser
import com.example.incorrect_warehouse_app.viewModel.LoginViewModel
import com.example.incorrect_warehouse_app.viewModel.ProfileViewModel
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.employee_list.*
import java.util.*

class ProfileActivity : AppCompatActivity() {

    //private lateinit var profileViewModel: ProfileViewModel

    //private var loginViewModel: LoginViewModel? = null
    //private var currentUser = loginViewModel?.currentUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        Log.d("TEST ProfileActivity:", "start")

        //surname.setText(intent.getStringExtra("EXTRA_SURNAME"))
//        name.setText(loginViewModel.currentUser?.name)

        val currUser = intent.getSerializableExtra("EXTRA_CURRENT_USER") as CurrentUser
        name.setText(currUser.name)
        surname.setText(currUser.surname)
        userLogin.setText(currUser.login)
        userEmail.setText(currUser.email)
        userAddress.setText(currUser.address)
        userSalary.setText(currUser.salary.toString() + " " + java.util.Currency.getInstance("GBP").getSymbol(
            Locale.ENGLISH
        ))
        userRole.setText(currUser.rolename)

        backToNavButton.setOnClickListener {
            Intent(this, NavigationActivity::class.java).also {
                Log.d("TEST LoginActivity:",currUser?.roleid.toString())
                it.putExtra("EXTRA_CURRENT_USER", currUser)
                startActivity(it)
            }
        }

        signOutButton.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
            finish()
        }
    }
}