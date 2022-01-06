package com.example.incorrect_warehouse_app.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.incorrect_warehouse_app.R
import com.example.incorrect_warehouse_app.model.CurrentUser
import com.example.incorrect_warehouse_app.view.activity.DisplayDataActivity
import com.example.incorrect_warehouse_app.view.activity.NavigationActivity
import com.example.incorrect_warehouse_app.view.activity.ProfileActivity
import kotlinx.android.synthetic.main.accountant_nav_fragment.*

class AccountantNavFragment: Fragment() {

    private var currUser: CurrentUser? = null

    companion object{
        fun newInstance() = WarehousemanNavFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //return super.onCreateView(inflater, container, savedInstanceState)

//        myProfileButton.setOnClickListener{
//            //Log.d("TEST","productsButton: before DisplayDataActivity start")
//            val intent = Intent(activity, DisplayDataActivity::class.java)
//            activity?.startActivity(intent)
//            //Log.d("TEST","productsButton: after DisplayDataActivity start")
//        }

        val bundle = arguments
        currUser = bundle!!.get("EXTRA_CURRENT_USER") as CurrentUser

        return inflater.inflate(R.layout.accountant_nav_fragment, container, false)



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        myProfileButton.setOnClickListener {
            activity?.let {
                val intent = Intent(it, ProfileActivity::class.java).also {
                    it.putExtra("EXTRA_CURRENT_USER", currUser)
                    //startActivity(it)
                }
                it.startActivity(intent)
            }
        }

//        productsButton.setOnClickListener {
//            activity?.let {
//                val intent = Intent(it, DisplayDataActivity::class.java)
//                it.startActivity(intent)
//            }
//        }

        productsButton.setOnClickListener {
            activity?.let {
                val intent = Intent(it, DisplayDataActivity::class.java).also {
                    it.putExtra("EXTRA_CURRENT_USER", currUser)
                    it.putExtra("EXTRA_LIST_TYPE", "Products")
                    //startActivity(it)
                }
                it.startActivity(intent)
            }
        }
    }

    //        myProfileButtonNav.setOnClickListener{
//            Log.d("TEST NavActivity:", "onClick")
//            Intent(this, ProfileActivity::class.java).also {
//                it.putExtra("EXTRA_CURRENT_USER", currUser)
//                startActivity(it)
//            }




}