package com.example.incorrect_warehouse_app.model

import android.os.StrictMode
import android.util.Log
import java.security.MessageDigest
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

class PostgreSqlDatabase {

    private val ip="192.168.1.19:5432"
    private val db="pi"
    private val username="pi"
    private val password="raspberry"

    fun dbConn(querry : String) {
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        var conn : Connection? = null
        var connString : String? = null
        try{
            Class.forName("org.postgresql.Driver")
            connString ="jdbc:postgresql://$ip/$db?user=$username&password=$password"
            conn = DriverManager.getConnection(connString)
        }catch (ex : SQLException){
            Log.e("SQLERROR: " , ex.message!!)
        }
        catch (ex1 :ClassNotFoundException){
            Log.e("ERROR :", ex1.message!!)
        }
        catch (ex2 :Exception){
            Log.e("Error :", ex2.message!!)
        }
        Log.d("TEST",conn?.isValid(1000).toString())
    }

    fun hashString(input: String): String {
        return input.toMD5()
    }

    fun String.toMD5(): String {
        val bytes = MessageDigest.getInstance("MD5").digest(this.toByteArray())
        return bytes.toHex()
    }

    fun ByteArray.toHex(): String {
        return joinToString("") { "%02x".format(it) }
    }
}