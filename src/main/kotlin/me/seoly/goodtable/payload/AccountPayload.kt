package me.seoly.goodtable.payload

import me.seoly.goodtable.core.model.type.AccountType
import java.time.LocalDateTime

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