package com.example.incorrect_warehouse_app.model

data class Reservation(
    val reservationid: Int,
    val employeeid: Int,
    val reservationdate: String,
    val productid: Int,
    val amount: Int
)
