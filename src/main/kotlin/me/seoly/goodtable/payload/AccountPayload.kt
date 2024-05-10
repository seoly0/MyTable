package me.seoly.goodtable.payload

class AccountPayload {

    class Request {
        class Create {
            var email: String = ""
            var password: String = ""
            var name: String = ""
            var phone: String = ""
        }
    }

    class Response {
        class Default: CommonPayload.ResponseBase() {
            var email: String? = null
            var name: String? = null
        }
    }
}