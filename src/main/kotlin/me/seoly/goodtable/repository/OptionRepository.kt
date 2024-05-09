package me.seoly.goodtable.repository

import me.seoly.goodtable.core.model.entity.OptionEntity
import org.springframework.data.jpa.repository.JpaRepository

interface OptionRepository: JpaRepository<OptionEntity, Long> {
}