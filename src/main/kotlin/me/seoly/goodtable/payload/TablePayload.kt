package me.seoly.goodtable.payload

import java.time.LocalDateTime

class TablePayload {

    class Request {

        data class Create(
            val name: String,
            val width: Int,
            val height: Int,
            val positionX: Int,
            val positionY: Int,
            val capacity: Int,
            val description: String = "",
            val memo: String = "",
        )
    }

    class Response {

        class Default: CommonPayload.ResponseBase() {
            var storeId: Long = 0
            var name: String = ""
            var capacity: Int = 0
            var width: Int = 0
            var height: Int = 0
            var positionX: Int = 0
            var positionY: Int = 0
            var description: String = ""
            var memo: String = ""
        }
    }
}