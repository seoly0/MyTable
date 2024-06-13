package me.seoly.mytable.controller

import io.swagger.v3.oas.annotations.tags.Tag
import me.seoly.mytable.payload.MemberPayload
import me.seoly.mytable.service.MemberService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "직원")
@RequestMapping("/v1/user")
@RestController
class MemberController (
    private val service: MemberService,
) {

    @PostMapping("/store/{storeId}/member")
    fun postStoreMember (
        @PathVariable storeId: Long,
        @RequestBody body: MemberPayload.Request.Create,
    ) = service.createMember(storeId, body)

    @GetMapping("/store/{storeId}/member/list")
    fun getStoreMemberList (
        @PathVariable storeId: Long,
    ) = service.serveStoreMemberList(storeId)

    @PatchMapping("/store/{storeId}/member/{memberId}/type")
    fun patchStoreMemberType (
        @PathVariable storeId: Long,
        @PathVariable memberId: Long,
        @RequestBody body: MemberPayload.Request.PatchMemberType,
    ) {

    }

    @DeleteMapping("/store/{storeId}/member/{memberId}")
    fun deleteStoreMember (
        @PathVariable storeId: Long,
        @PathVariable memberId: Long,
    ) {

    }
}