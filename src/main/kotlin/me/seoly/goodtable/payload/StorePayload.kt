package me.seoly.goodtable.payload

class StorePayload {

    class Request {
        data class Create(
            val name: String,
            val address: String,
            val ownerId: Long,
            val bizNumber: String,
            val tel: String,
        )
    }

    class Response {
        class Default: CommonPayload.ResponseBase() {
            lateinit var name: String
        }
    }
}