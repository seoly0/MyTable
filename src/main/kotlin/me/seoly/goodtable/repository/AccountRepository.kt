package me.seoly.goodtable.repository

import me.seoly.goodtable.core.model.entity.AccountEntity
import org.springframework.data.jpa.repository.JpaRepository

interface AccountRepository: JpaRepository<AccountEntity, Long> {
//    fun getById
}