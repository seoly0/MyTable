package me.seoly.mytable.repository

import me.seoly.mytable.core.model.entity.AccountEntity
import org.springframework.data.jpa.repository.JpaRepository

interface AccountRepository: JpaRepository<AccountEntity, Long> {
//    fun getById
    fun findByEmail(email: String): AccountEntity?
}