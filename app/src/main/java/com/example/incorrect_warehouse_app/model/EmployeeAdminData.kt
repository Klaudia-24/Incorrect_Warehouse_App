package com.example.incorrect_warehouse_app.model

data class EmployeeAdminData(

    val employeeid: Int,
    val name: String,
    val surname: String,
    val salary: Float,
    val address: String,
    val login: String,
    val email: String,
    val rolename: String
)

//    employeeId serial PRIMARY KEY,
//    login VARCHAR ( 40 ) NOT NULL,
//    password VARCHAR ( 100 ) NOT NULL,
//    email VARCHAR ( 50 ) NOT NULL,



//employeeId serial PRIMARY KEY,
//name VARCHAR ( 30 ) NOT NULL,
//surname VARCHAR ( 40 ) NOT NULL,
//login VARCHAR ( 40 ) NOT NULL,
//email VARCHAR ( 50 ) NOT NULL,
//roleName VARCHAR ( 30 ) NOT NULL