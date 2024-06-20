package me.seoly.mytable.serializer

import me.seoly.mytable.core.model.type.MemberType

class MemberSerializer {

    class Request {

        class Create {
            val accountId: Long = 0
            lateinit var type: MemberType
        }

        class PatchMemberType {
            lateinit var type: MemberType
        }
    }

    class Response {

        class Default: CommonSerializer.ResponseBase() {
            var storeId: Long = 0
            var accountId: Long = 0
            lateinit var type: MemberType
        }
    }
}