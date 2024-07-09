package me.seoly.mytable.repository

import me.seoly.mytable.core.model.entity.OrderEntity
import me.seoly.mytable.core.model.type.OrderStateType
import me.seoly.mytable.repository.projection.OrderWithoutDetails
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.web.PageableDefault
import java.time.LocalDate
import java.time.LocalDateTime

interface OrderRepository: JpaRepository<OrderEntity, Long> {

    fun findByIdAndStoreIdOrCustomerId(id: Long, storeId: Long?, customerId: Long?): OrderEntity
    fun findByStoreIdAndState(storeId: Long, state: OrderStateType): OrderEntity
    fun findAllByStoreId(storeId: Long): List<OrderEntity>
    fun findAllByStoreIdAndState(storeId: Long, state: OrderStateType?): List<OrderEntity>
    fun findByCustomerIdAndState(customerId: Long, state: OrderStateType): OrderEntity
    fun findAllByCustomerId(customerId: Long): List<OrderEntity>
    fun findAllByCustomerIdAndState(customerId: Long, state: OrderStateType): List<OrderEntity>
    fun findByIdAndStoreId(id: Long, storeId: Long): OrderEntity?
    fun findTop5ByStoreIdOrCustomerIdOrderByCreatedAtDesc(storeId: Long, customerId: Long): List<OrderWithoutDetails>
    fun findAllByStoreIdAndCustomerId(storeId: Long, customerId: Long): List<OrderWithoutDetails>
    fun findAllByStoreIdAndCustomerIdAndAtAfter(storeId: Long, customerId: Long, at: LocalDateTime?): List<OrderWithoutDetails>
}