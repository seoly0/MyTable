package me.seoly.goodtable.exception

import org.springframework.http.HttpStatus
open class ApplicationException: Exception() {

    open val status: HttpStatus = HttpStatus.BAD_REQUEST
    val map: Map<String, String?>
        get() = mapOf(
            "message" to this.message,
        )
}