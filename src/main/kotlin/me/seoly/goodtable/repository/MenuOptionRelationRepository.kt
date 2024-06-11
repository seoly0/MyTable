package me.seoly.goodtable.repository

import me.seoly.goodtable.core.model.entity.MenuOptionRelation
import me.seoly.goodtable.core.model.entity.OptionEntity
import org.springframework.data.jpa.repository.JpaRepository

interface MenuOptionRelationRepository: JpaRepository<MenuOptionRelation, Long> {
    fun findAllByStoreIdAndMenuId(storeId: Long, menuId: Long): List<MenuOptionRelation>
}