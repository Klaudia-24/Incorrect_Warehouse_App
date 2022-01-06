package com.example.incorrect_warehouse_app.model

data class Reservation(
    val reservationid: Int,
    val employeeid: Int,
    val employeename: String,
    val employeesurname: String,
    val reservationdate: String,
    val name: String,
    val sizeofproduct: Int,
    val price: Float,
    val amount: Int
)


//data class Reservation(
//    val reservationid: Int,
//    val employeeid: Int,
//    val reservationdate: String,
//    val productid: Int,
//    val amount: Int
//)
