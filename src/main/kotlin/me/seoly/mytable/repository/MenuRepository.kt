package me.seoly.mytable.repository

import me.seoly.mytable.core.model.entity.MenuEntity
import org.springframework.data.jpa.repository.JpaRepository

interface MenuRepository: JpaRepository<MenuEntity, Long> {

    fun findAllByStoreId(storeId: Long): List<MenuEntity>
    fun findByIdAndStoreId(id: Long, storeId: Long): MenuEntity?
    fun findAllByStoreIdAndIdIn(storeId: Long, ids: List<Long>): List<MenuEntity>
    fun findAllByStoreIdAndCategoryId(storeId: Long, categoryId: Long): List<MenuEntity>

}