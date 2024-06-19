package me.seoly.mytable.repository

import me.seoly.mytable.core.model.entity.OptionEntity
import org.springframework.data.jpa.repository.JpaRepository

interface OptionRepository: JpaRepository<OptionEntity, Long> {

    fun findAllByIdInAndStoreId(ids: List<Long>, storeId: Long): List<OptionEntity>
}