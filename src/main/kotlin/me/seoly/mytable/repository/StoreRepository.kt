package me.seoly.mytable.repository

import me.seoly.mytable.core.model.entity.StoreEntity
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface StoreRepository: JpaRepository<StoreEntity, Long> {
    fun getAllByOwnerId(ownerId: Long): List<StoreEntity>
    fun getAllByNameContains(name: String, pageable: Pageable): List<StoreEntity>
}