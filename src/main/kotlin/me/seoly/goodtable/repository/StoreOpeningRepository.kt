package me.seoly.goodtable.repository

import me.seoly.goodtable.core.model.entity.StoreOpeningEntity
import org.springframework.data.jpa.repository.JpaRepository


interface StoreOpeningRepository: JpaRepository<StoreOpeningEntity, Long> {

    fun findAllByStoreId(storeId: Long): List<StoreOpeningEntity>
}