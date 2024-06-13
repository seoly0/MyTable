package me.seoly.mytable.repository

import me.seoly.mytable.core.model.entity.MemberEntity
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository: JpaRepository<MemberEntity, Long> {

    fun findAllByStoreId(storeId: Long): List<MemberEntity>
}