package me.seoly.mytable.repository.projection

import me.seoly.mytable.core.model.type.OrderStateType
import me.seoly.mytable.core.model.type.OrderType
import java.time.LocalDateTime

interface OrderWithoutDetails {
    val id: Long
    val createdAt: LocalDateTime
    val updatedAt: LocalDateTime
    val customerId: Long
    val storeId: Long
    val tableId: Long
    val at: LocalDateTime
    val numOfPeople: Int
    val type: OrderType
    val state: OrderStateType
    val script: String
    val totalPrice: Int
}