package me.seoly.goodtable.exception

import org.springframework.http.HttpStatus

abstract class ApplicationException: Exception() {

    abstract val status: HttpStatus
    val map: Map<String, String?>
        get() = mapOf(
            "message" to this.message,
        )
}