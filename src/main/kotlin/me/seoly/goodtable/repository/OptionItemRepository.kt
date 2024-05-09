package me.seoly.goodtable.repository

import me.seoly.goodtable.core.model.entity.OptionItemEntity
import org.springframework.data.jpa.repository.JpaRepository

interface OptionItemRepository: JpaRepository<OptionItemEntity, Long> {
}