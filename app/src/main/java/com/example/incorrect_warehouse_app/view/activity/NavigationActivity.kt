package com.example.incorrect_warehouse_app.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.incorrect_warehouse_app.R
import com.example.incorrect_warehouse_app.model.CurrentUser
import com.example.incorrect_warehouse_app.view.fragment.AccountantNavFragment
import com.example.incorrect_warehouse_app.view.fragment.AdministratorNavFragment
import com.example.incorrect_warehouse_app.view.fragment.SalesRepNavFragment
import com.example.incorrect_warehouse_app.view.fragment.WarehousemanNavFragment


class NavigationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)

        val currUser = intent.getSerializableExtra("EXTRA_CURRENT_USER") as CurrentUser
//        val adminFragment = AdministratorNavFragment()
//        val warManFragment = WarehousemanNavFragment()
//        val salRepFragment = SalesRepNavFragment()
//        val accFragment = AccountantNavFragment()

        //var navFragment: Fragment

        Log.d("TEST NavActivity:",currUser.roleid.toString())

//        when(currUser.roleid){
//            //setFragment(WarehousemanNavFragment.newInstance())
//
//            "admin" -> navFragment = AdministratorNavFragment()
//            "warMan" -> navFragment = WarehousemanNavFragment()
//            "salRep" -> navFragment = SalesRepNavFragment()
//            "acc" -> navFragment = AccountantNavFragment()
//
//            "admin" -> supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, adminFragment).commit()
//            "warMan" -> supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, warManFragment).commit()
//            "salRep" -> supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, salRepFragment).commit()
//            "acc" -> supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, accFragment).commit()
//        }

        val navFragment: Fragment? = when (currUser.roleid) {
            "admin" -> AdministratorNavFragment()
            "warMan" -> WarehousemanNavFragment()
            "salRep" -> SalesRepNavFragment()
            "acc" -> AccountantNavFragment()
            else -> null
        }

        if (navFragment != null) {
            val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            val bundle = Bundle()
            bundle.putSerializable("EXTRA_CURRENT_USER", currUser)
            navFragment.arguments = bundle
            fragmentTransaction.add(R.id.fragmentContainer, navFragment).commit()
        }





        //Log.d("TEST","NavigationActivity start")

//        productsButton.setOnClickListener{
//            //Log.d("TEST","productsButton: before DisplayDataActivity start")
//            val intent = Intent(this, DisplayDataActivity::class.java)
//            startActivity(intent)
//            //Log.d("TEST","productsButton: after DisplayDataActivity start")
//        }
//
//        myProfileButtonNav.setOnClickListener{
//            Log.d("TEST NavActivity:", "onClick")
//            Intent(this, ProfileActivity::class.java).also {
//                it.putExtra("EXTRA_CURRENT_USER", currUser)
//                startActivity(it)
//            }
//
//        }
    }

    private fun setFragment(fragment: Fragment){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, fragment)
        fragmentTransaction.commit()
    }


}