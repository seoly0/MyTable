package me.seoly.mytable.controller

import io.swagger.v3.oas.annotations.tags.Tag
import me.seoly.mytable.serializer.TablePayload
import me.seoly.mytable.service.TableService
import org.springframework.web.bind.annotation.*

@Tag(name = "테이블")
@RequestMapping("/v1/user")
@RestController
class TableController(
    private val tableService: TableService
) {

    @PostMapping("/{storeId}/table")
    fun postStoreTable(
        @PathVariable storeId: Long,
        @RequestBody body: TablePayload.Request.Create,
    ) = tableService.createTable(storeId, body)

    @GetMapping("/{storeId}/table/list")
    fun getStoreTableList(
        @PathVariable storeId: Long
    ) = tableService.serveTableList(storeId)

    @GetMapping("/{storeId}/table/{tableId}")
    fun getStoreTable(
        @PathVariable storeId: Long,
        @PathVariable tableId: Long,
    ) = tableService.serveTableDetail(storeId, tableId)

    @PutMapping("/{storeId}/table/{tableId}")
    fun putStoreTable(@PathVariable storeId: Long, @PathVariable tableId: Long) {
        TODO()
    }

    @DeleteMapping("/{storeId}/table/{tableId}")
    fun deleteStoreTable(@PathVariable storeId: Long, @PathVariable tableId: Long) {
        TODO()
    }
}