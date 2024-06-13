package me.seoly.mytable.repository

import me.seoly.mytable.core.model.entity.MenuOptionRelation
import org.springframework.data.jpa.repository.JpaRepository

interface MenuOptionRelationRepository: JpaRepository<MenuOptionRelation, Long> {
    fun findAllByStoreIdAndMenuId(storeId: Long, menuId: Long): List<MenuOptionRelation>
}