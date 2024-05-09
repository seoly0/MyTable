package me.seoly.goodtable.repository

import me.seoly.goodtable.core.model.entity.StoreEntity
import org.springframework.data.jpa.repository.JpaRepository

interface StoreRepository: JpaRepository<StoreEntity, Long> {
    fun getAllByOwnerId(ownerId: Long): List<StoreEntity>
}