package com.example.incorrect_warehouse_app.utils

import java.security.MessageDigest

class HashString {

    private fun ByteArray.toHex(): String {
        return joinToString("") { "%02x".format(it) }
    }

    private fun String.toMD5(): String {
        val bytes = MessageDigest.getInstance("MD5").digest(this.toByteArray())
        return bytes.toHex()
    }

    fun hashString(input: String): String {
        return input.toMD5()
    }
}