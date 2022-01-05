package com.example.incorrect_warehouse_app.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.incorrect_warehouse_app.R

class AdministratorNavFragment: Fragment() {

    companion object{
        fun newInstance() = WarehousemanNavFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //return super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.administrator_nav_fragment, container, false)
    }
}