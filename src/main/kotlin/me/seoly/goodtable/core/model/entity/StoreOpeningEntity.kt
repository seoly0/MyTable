package me.seoly.goodtable.core.model.entity

import jakarta.persistence.*
import me.seoly.spring.jpa.BaseEntity
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.SQLRestriction
import java.time.DayOfWeek


@Entity
@Table(
    name = "store_opening",
    indexes = []
)
@SQLDelete(sql = "UPDATE store_opening SET deleted = true WHERE id = ?")
@SQLRestriction("deleted = false")
@DynamicInsert
@DynamicUpdate
class StoreOpeningEntity(
    @Column(name = "store_id")
    var storeId: Long,

    @Column
    @Enumerated(EnumType.ORDINAL)
    var dayOfWeek: DayOfWeek,

    @Column
    var openTime: String,

    @Column
    var closeTime: String,

    @Column
    var lastOrderTime: String,

): BaseEntity() {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false, insertable = false, updatable = false)
    lateinit var store: StoreEntity
}
