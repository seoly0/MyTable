package me.seoly.mytable.controller

import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.servlet.http.HttpServletRequest
import me.seoly.mytable.serializer.AccountPayload
import me.seoly.mytable.service.AccountService
import org.springframework.http.HttpHeaders
import org.springframework.web.bind.annotation.*

@Tag(name = "계정")
@RequestMapping("/v1/user")
@RestController
class AccountController(
    private val accountService: AccountService,
) {
    @PostMapping("/account")
    fun postAccount(
        request: HttpServletRequest,
        @RequestHeader headers: HttpHeaders,
        @RequestBody body: AccountPayload.Request.Create,
    ) = accountService.createAccount(body)

    @GetMapping(path = [
        "/account/me",
        "/account/{accountId}",
    ])
    fun getAccount(
        @PathVariable accountId: Long?
    ) = accountService.serveAccount(accountId)

    @PutMapping("/account")
    fun putAccount() {
        TODO()
    }

    @DeleteMapping("/account")
    fun deleteAccount() {
        TODO()
    }
}