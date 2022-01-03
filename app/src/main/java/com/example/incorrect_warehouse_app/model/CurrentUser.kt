package com.example.incorrect_warehouse_app.model

data class CurrentUser(
    val employeeid: Int,
    val name: String,
    val surname: String,
    val salary: Float,
    val address: String,
    val login: String,
    val email: String,
    val roleId: Int
)
