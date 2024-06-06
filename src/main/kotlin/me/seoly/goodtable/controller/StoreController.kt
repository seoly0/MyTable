package me.seoly.goodtable.controller

import io.swagger.v3.oas.annotations.tags.Tag
import me.seoly.goodtable.payload.StorePayload
import me.seoly.goodtable.service.StoreService
import org.springframework.web.bind.annotation.*

@Tag(name = "상점")
@RequestMapping("/v1/user")
@RestController
class StoreController(
    private val storeService: StoreService,
) {

    @PostMapping("/store")
    fun postStore(
        @RequestBody body: StorePayload.Request.Create,
    ) = storeService.createStore(body)

    @GetMapping("/store/my")
    fun getMyStore(

    ) = storeService.serveMyStoreList(1)

    @GetMapping("/store/{storeId}")
    fun getStore(
        @PathVariable storeId: Long,
    ) = storeService.serveStore(storeId)

    @PutMapping("/store/{storeId}")
    fun putStore(
        @PathVariable storeId: Long,
    ) {
        TODO()
    }

    @DeleteMapping("/store/{storeId}")
    fun deleteStore(
        @PathVariable storeId: Long,
    ) {
        TODO()
    }
}