package me.seoly.goodtable.payload

import jakarta.servlet.ServletRequest
import java.time.LocalDateTime
import java.util.Date

class CommonPayload {
    data class Response<Content: Any?> (
        val success: Boolean = true,
        val request: Request,
        val contents: Content,
        val message: Any? = null,
        val timestamp: LocalDateTime = LocalDateTime.now(),
    )

    open class ResponseBase {
        var id: Long? = null
        var createdAt: String = ""
        var updatedAt: String = ""
    }

    data class Request (
        val path: Any,
        val query: Any?,
        val body: Any?,
    )

    class Query {
        data class SortBy(
            val sort: String
        )
    }
}