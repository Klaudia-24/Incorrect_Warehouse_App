package com.example.incorrect_warehouse_app.model

data class Reservation(
    val reservationId: Int,
    val employeeId: Int,
    val reservationDate: String,
    val productId: Int,
    val amount: Int
)
