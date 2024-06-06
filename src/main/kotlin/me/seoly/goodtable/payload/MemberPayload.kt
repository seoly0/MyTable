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

        class Default {
            val id: Long = 0
            lateinit var createdAt: LocalDateTime
            lateinit var updatedAt: LocalDateTime
            val storeId: Long = 0
            val accountId: Long = 0
            lateinit var memberType: MemberType
        }
    }
}