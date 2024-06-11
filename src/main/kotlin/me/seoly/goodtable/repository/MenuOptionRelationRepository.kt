package me.seoly.goodtable.repository

import me.seoly.goodtable.core.model.entity.MenuOptionRelation
import org.springframework.data.jpa.repository.JpaRepository

interface MenuOptionRelationRepository: JpaRepository<MenuOptionRelation, Long> {
}