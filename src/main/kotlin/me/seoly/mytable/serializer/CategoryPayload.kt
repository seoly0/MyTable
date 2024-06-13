package me.seoly.mytable.serializer

class CategoryPayload {

    class Request {

        class Create {
            val name: String = ""
        }
    }

    class Response {

        class Default: CommonPayload.ResponseBase() {
            var storeId: Long = 0
            lateinit var name: String
        }
    }
}