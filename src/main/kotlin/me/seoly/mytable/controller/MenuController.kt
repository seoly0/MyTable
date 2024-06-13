package me.seoly.mytable.controller

import io.swagger.v3.oas.annotations.tags.Tag
import me.seoly.mytable.payload.CategoryPayload
import me.seoly.mytable.payload.MenuPayload
import me.seoly.mytable.service.MenuService
import org.springframework.web.bind.annotation.*

@Tag(name = "메뉴")
@RequestMapping("/v1/user/store")
@RestController
class MenuController (
    private val menuService: MenuService
) {

    @PostMapping("/{storeId}/menuCategory")
    fun postStoreMenuCategory (
        @PathVariable storeId: Long,
        @RequestBody body: CategoryPayload.Request.Create,
    ) = menuService.createMenuCategory(storeId, body)

    @GetMapping("/{storeId}/menuCategory/list")
    fun getStoreMenuCategoryList (
        @PathVariable storeId: Long
    ) = menuService.serveStoreMenuCategoryList(storeId)

    @GetMapping("/{storeId}/menuCategory/{categoryId}/menu/list")
    fun getStoreMenuListByCategory (
        @PathVariable storeId: Long,
        @PathVariable categoryId: Long,
    ) = menuService.serveMenuListByCategory(storeId, categoryId)

    @PutMapping("/{storeId}/menuCategory/{categoryId}")
    fun putStoreMenuCategory (
        @PathVariable storeId: Long,
        @PathVariable categoryId: Long,
    ) {
        TODO()
    }

    @DeleteMapping("/{storeId}/menuCategory/{categoryId}")
    fun deleteStoreMenuCategory (
        @PathVariable storeId: Long,
        @PathVariable categoryId: Long,
    ) {
        TODO()
    }

    @PostMapping("/{storeId}/menu")
    fun postStoreMenu (
        @PathVariable storeId: Long,
        @RequestBody body: MenuPayload.Request.Create,
    ) = menuService.createMenu(storeId, body)

    @GetMapping("/{storeId}/menu/list")
    fun getStoreMenuList(
        @PathVariable storeId: Long
    ) = menuService.serveMenuList(storeId)

    @GetMapping("/{storeId}/menu/{menuId}")
    fun getStoreMenu(
        @PathVariable storeId: Long,
        @PathVariable menuId: Long,
    ) = menuService.serveMenuDetail(storeId, menuId)

    @PutMapping("/{storeId}/menu/{menuId}")
    fun putStoreMenu(
        @PathVariable storeId: Long,
        @PathVariable menuId: Long,
    ) {
        TODO()
    }

    @DeleteMapping("/{storeId}/menu/{menuId}")
    fun deleteStoreMenu(
        @PathVariable storeId: Long,
        @PathVariable menuId: Long,
    ) {
        TODO()
    }

    @GetMapping("/{storeId}/menu/{menuId}/option/list")
    fun getStoreMenuOptionList(
        @PathVariable storeId: Long,
        @PathVariable menuId: Long,
    ) = menuService.serveMenuOption(storeId, menuId)

    @DeleteMapping("/{storeId}/menu/{menuId}/option/{optionId}")
    fun deleteStoreMenuOption(
        @PathVariable storeId: Long,
        @PathVariable menuId: Long,
        @PathVariable optionId: Long,
    ) {

    }

    @PostMapping("/{storeId}/menu/{menuId}/option/{optionId}/item")
    fun postStoreMenuOptionItem(
        @PathVariable storeId: Long,
        @PathVariable menuId: Long,
        @PathVariable optionId: Long,
    ) = menuService.createOptionItem(storeId, menuId, optionId)

    @GetMapping("/{storeId}/menu/{menuId}/option/{optionId}/item/list")
    fun getStoreMenuOptionItemList(
        @PathVariable storeId: Long,
        @PathVariable menuId: Long,
        @PathVariable optionId: Long,
    ) {

    }

    @PutMapping("/{storeId}/menu/{menuId}/option/{optionId}/item/{itemId}")
    fun putStoreMenuOptionItem(
        @PathVariable storeId: Long,
        @PathVariable menuId: Long,
        @PathVariable optionId: Long,
        @PathVariable itemId: Long,
    ) {

    }

    @DeleteMapping("/{storeId}/menu/{menuId}/option/{optionId}/item/{itemId}")
    fun deleteStoreMenuOptionItem(
        @PathVariable storeId: Long,
        @PathVariable menuId: Long,
        @PathVariable optionId: Long,
        @PathVariable itemId: Long,
    ) {

    }
}