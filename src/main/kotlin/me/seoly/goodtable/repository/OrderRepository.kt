package me.seoly.goodtable.repository

import me.seoly.goodtable.core.model.entity.OrderEntity
import me.seoly.goodtable.core.model.type.OrderStateType
import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository: JpaRepository<OrderEntity, Long> {

    fun findAllByStoreIdAndState(storeId: Long, state: OrderStateType): List<OrderEntity>
}