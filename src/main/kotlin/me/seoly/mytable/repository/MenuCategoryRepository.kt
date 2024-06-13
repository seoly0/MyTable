package me.seoly.mytable.repository

import me.seoly.mytable.core.model.entity.MenuCategoryEntity
import org.springframework.data.jpa.repository.JpaRepository

interface MenuCategoryRepository: JpaRepository<MenuCategoryEntity, Long> {

    fun findAllByStoreId(storeId: Long): List<MenuCategoryEntity>
}