package me.seoly.mytable.repository

import me.seoly.mytable.core.model.entity.OrderEntity
import me.seoly.mytable.core.model.type.OrderStateType
import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository: JpaRepository<OrderEntity, Long> {

    fun findByIdAndStoreIdOrCustomerId(id: Long, storeId: Long?, customerId: Long?): OrderEntity
    fun findByStoreIdAndState(storeId: Long, state: OrderStateType): OrderEntity
    fun findAllByStoreId(storeId: Long): List<OrderEntity>
    fun findAllByStoreIdAndState(storeId: Long, state: OrderStateType?): List<OrderEntity>
    fun findByCustomerIdAndState(customerId: Long, state: OrderStateType): OrderEntity
    fun findAllByCustomerId(customerId: Long): List<OrderEntity>
    fun findAllByCustomerIdAndState(customerId: Long, state: OrderStateType): List<OrderEntity>

    fun findByIdAndStoreId(id: Long, storeId: Long): OrderEntity?
}