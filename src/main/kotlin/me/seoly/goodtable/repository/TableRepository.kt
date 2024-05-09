package me.seoly.goodtable.repository

import me.seoly.goodtable.core.model.entity.TableEntity
import me.seoly.goodtable.payload.TablePayload
import org.springframework.data.jpa.repository.JpaRepository

interface TableRepository: JpaRepository<TableEntity, Long> {

    fun findAllByStoreId(storeId: Long): List<TableEntity>
    fun findByIdAndStoreId(id: Long, storeId: Long): TableEntity
//    fun findBy
}