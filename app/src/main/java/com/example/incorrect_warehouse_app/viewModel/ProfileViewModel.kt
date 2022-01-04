package com.example.incorrect_warehouse_app.viewModel

import androidx.lifecycle.ViewModel
import com.example.incorrect_warehouse_app.model.CurrentUser
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.incorrect_warehouse_app.R
import com.example.incorrect_warehouse_app.viewModel.ProfileViewModel
import kotlinx.android.synthetic.main.activity_profile.*
import com.google.android.material.textview.MaterialTextView

class ProfileViewModel: ViewModel() {

    private lateinit var loginViewModel: LoginViewModel
    private var currentUser = loginViewModel.currentUser

    fun setCurrentUserData(currentUser: CurrentUser){

        
    }

}