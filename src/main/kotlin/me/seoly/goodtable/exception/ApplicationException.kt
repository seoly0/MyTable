package me.seoly.goodtable.exception

import org.springframework.http.HttpStatus

open class ApplicationException: Exception() {

    lateinit var status: HttpStatus
    val map: Map<String, String?>
        get() = mapOf(
            "message" to this.message,
        )
}