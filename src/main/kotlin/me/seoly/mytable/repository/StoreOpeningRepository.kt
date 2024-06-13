package me.seoly.mytable.repository

import me.seoly.mytable.core.model.entity.StoreOpeningEntity
import org.springframework.data.jpa.repository.JpaRepository


interface StoreOpeningRepository: JpaRepository<StoreOpeningEntity, Long> {

    fun findAllByStoreId(storeId: Long): List<StoreOpeningEntity>
}