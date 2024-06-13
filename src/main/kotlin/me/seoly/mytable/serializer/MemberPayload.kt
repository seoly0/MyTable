package me.seoly.mytable.serializer

import me.seoly.mytable.core.model.type.MemberType

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