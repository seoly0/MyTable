package me.seoly.goodtable.payload

import me.seoly.goodtable.core.model.type.MemberType
import java.time.LocalDateTime

class MemberPayload {

    class Request {

        class Create {
            val accountId: Long = 0
            lateinit var memberType: MemberType
        }

        class PatchMemberType {
            lateinit var memberType: MemberType
        }
    }

    class Response {

        class Default: CommonPayload.ResponseBase() {
            var storeId: Long = 0
            var accountId: Long = 0
            lateinit var memberType: MemberType
        }
    }
}