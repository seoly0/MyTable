package me.seoly.mytable.service

import me.seoly.mytable.core.model.entity.StoreTableEntity
import me.seoly.mytable.exception.EntityNotExistException
import me.seoly.mytable.payload.TablePayload
import me.seoly.mytable.repository.StoreTableRepository
import me.seoly.utils.ModelMapper
import org.springframework.stereotype.Service

@Service
class TableService(
    private val repository: StoreTableRepository,
    private val modelMapper: ModelMapper,
) {

    fun createTable(storeId: Long, payload: TablePayload.Request.Create): TablePayload.Response.Default {

        val entity = modelMapper.map(payload, StoreTableEntity::class.java)
        entity.storeId = storeId

        repository.save(entity)

        return modelMapper.map(entity, TablePayload.Response.Default::class.java)
    }

    fun serveTableList(storeId: Long) = repository.findAllByStoreId(storeId).map {
        println(it)
        modelMapper.map(it, TablePayload.Response.Default::class.java)
    }

    fun serveTableDetail(storeId: Long, tableId: Long): TablePayload.Response.Default {

        val entity = repository.findByIdAndStoreId(tableId, storeId) ?: throw EntityNotExistException()

        return modelMapper.map(entity, TablePayload.Response.Default::class.java)
    }

}