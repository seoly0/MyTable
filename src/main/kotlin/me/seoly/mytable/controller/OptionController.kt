package me.seoly.mytable.controller

import io.swagger.v3.oas.annotations.tags.Tag
import me.seoly.mytable.serializer.OptionSerializer
import me.seoly.mytable.service.MenuService
import org.springframework.web.bind.annotation.*

@Tag(name = "옵션")
@RequestMapping("/v1/user/store/{storeId}")
@RestController
class OptionController (
    private val menuService: MenuService
) {

    @PostMapping("/option")
    fun postStoreMenuOption(
        @PathVariable storeId: Long,
        @RequestBody body: OptionSerializer.Request.Create,
    ) = menuService.createOption(storeId, body)

    @GetMapping("/option/{optionId}")
    fun getStoreOption(
        @PathVariable storeId: Long,
        @PathVariable optionId: Long,
    ) = menuService.serveOption(storeId, optionId)

    @PutMapping("/option/{optionId}")
    fun putStoreOption(
        @PathVariable storeId: Long,
        @PathVariable optionId: Long,
    ) {
        TODO()
    }

    @DeleteMapping("/option/{optionId}")
    fun deleteStoreOption(
        @PathVariable storeId: Long,
        @PathVariable optionId: Long,
    ) {
        TODO()
    }
}