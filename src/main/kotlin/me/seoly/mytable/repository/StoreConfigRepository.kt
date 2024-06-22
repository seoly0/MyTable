package me.seoly.mytable.repository

import me.seoly.mytable.core.model.entity.StoreConfigEntity
import org.springframework.data.jpa.repository.JpaRepository

interface StoreConfigRepository: JpaRepository<StoreConfigEntity, Long> {

}