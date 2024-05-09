package me.seoly.goodtable.repository

import me.seoly.goodtable.core.model.entity.MemberEntity
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository: JpaRepository<MemberEntity, Long> {

    fun findAllByStoreId(storeId: Long): List<MemberEntity>
}