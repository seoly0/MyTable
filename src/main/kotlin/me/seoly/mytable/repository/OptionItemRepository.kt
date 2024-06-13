package me.seoly.mytable.repository

import me.seoly.mytable.core.model.entity.OptionItemEntity
import org.springframework.data.jpa.repository.JpaRepository

interface OptionItemRepository: JpaRepository<OptionItemEntity, Long> {
}