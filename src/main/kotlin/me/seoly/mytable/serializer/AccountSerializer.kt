package me.seoly.mytable.serializer

class AccountSerializer {

    class Request {
        class Create {
            var email: String = ""
            var password: String = ""
            var name: String = ""
            var phone: String = ""
        }
    }

    class Response {
        class Default: CommonSerializer.ResponseBase() {
            var email: String? = null
            var name: String? = null
        }
    }
}