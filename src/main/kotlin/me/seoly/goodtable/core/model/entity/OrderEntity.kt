package me.seoly.goodtable.core.model.entity

import jakarta.persistence.*
import me.seoly.goodtable.core.model.type.OrderStateType
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.SQLRestriction
import me.seoly.spring.jpa.BaseEntity
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate
import java.time.LocalDateTime

@Entity
@Table(
    name = "order",
    indexes = []
)
@SQLDelete(sql = "UPDATE order SET deleted = true WHERE id = ?")
@SQLRestriction("deleted = false")
@DynamicInsert
@DynamicUpdate
data class OrderEntity (
    @Column(name = "customer_id")
    var customerId: Long,

    @Column(name = "store_id")
    var storeId: Long,

    @Column(name = "table_id")
    var tableId: Long,

    @Column
    var at: LocalDateTime,

    @Column
    var numOfPeople: Int,

    @Column
    @Enumerated(EnumType.STRING)
    var state: OrderStateType,
): BaseEntity()  {
    @Column
    lateinit var details: String

    @Column
    var totalPrice: Int = 0

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false, insertable = false, updatable = false)
    lateinit var customer: AccountEntity

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false, insertable = false, updatable = false)
    lateinit var store: StoreEntity

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "table_id", nullable = false, insertable = false, updatable = false)
    lateinit var table: StoreTableEntity
}