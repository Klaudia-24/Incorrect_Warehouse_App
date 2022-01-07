package com.example.incorrect_warehouse_app.model

data class EmployeeLoginDataRequest(

    val employeeid: Int,
    val login: String,
    val password: String,
    val email: String,
)