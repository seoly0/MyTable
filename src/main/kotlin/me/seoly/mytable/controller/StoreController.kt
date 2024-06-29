package me.seoly.mytable.controller

import io.swagger.v3.oas.annotations.tags.Tag
import me.seoly.mytable.serializer.StoreSerializer
import me.seoly.mytable.service.StoreService
import org.springdoc.core.annotations.ParameterObject
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*


@Tag(name = "상점")
@RequestMapping("/v1/user")
@RestController
class StoreController(
    private val storeService: StoreService,
) {

    @PostMapping("/store")
    fun postStore(
        @RequestBody body: StoreSerializer.Request.Create,
    ) = storeService.createStore(body)

    @GetMapping("/store/list")
    fun getStoreList(
        @ParameterObject filter: StoreSerializer.Filter,
        @ParameterObject pageable: Pageable,
    ) = storeService.searchStore(filter.name, pageable)

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

    @GetMapping("/store/{storeId}/opened")
    fun getStoreOpened(
        @PathVariable storeId: Long,
    ) = storeService.getStoreOpened(storeId)

    @PatchMapping("/store/{storeId}/opened")
    fun patchStoreOpened(
        @PathVariable storeId: Long,
        @RequestBody patch: StoreSerializer.Request.PatchOpened,
    ) = storeService.setStoreOpened(storeId, patch.opened)


    @PostMapping("/store/{storeId}/opening")
    fun postStoreOpening(
        @PathVariable storeId: Long,
        @RequestBody create: List<StoreSerializer.Request.Opening>,
    ) = storeService.setStoreOpening(storeId, create)


    @GetMapping("/store/{storeId}/opening")
    fun getStoreOpening(
        @PathVariable storeId: Long,
    ) = storeService.serveStoreOpening(storeId)
}