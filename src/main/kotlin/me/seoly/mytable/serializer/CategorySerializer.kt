package me.seoly.mytable.serializer

class CategorySerializer {

    class Request {

        class Create {
            val name: String = ""
        }
    }

    class Response {

        class Default: CommonSerializer.ResponseBase() {
            var storeId: Long = 0
            lateinit var name: String
        }
    }
}