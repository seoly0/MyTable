package me.seoly.mytable.serializer

import java.time.LocalDateTime

class CommonSerializer {
    data class Response<Content: Any?> (
        val success: Boolean = true,
        val request: Request,
        val contents: Content,
        val message: Any? = null,
        val timestamp: LocalDateTime = LocalDateTime.now(),
    )

    open class ResponseBase {
        var id: Long = 0
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