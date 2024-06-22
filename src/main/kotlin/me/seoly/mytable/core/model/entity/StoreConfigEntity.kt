package me.seoly.mytable.core.model.entity

import jakarta.persistence.*
import me.seoly.spring.jpa.BaseEntity
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.SQLRestriction

@Entity
@Table(
    name = "store_config",
    indexes = []
)
@SQLDelete(sql = "UPDATE store_config SET deleted = true WHERE id = ?")
@SQLRestriction("deleted = false")
@DynamicInsert
@DynamicUpdate
class StoreConfigEntity(
    @Column(name = "store_id")
    var storeId: Long,
): BaseEntity() {


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false, insertable = false, updatable = false)
    lateinit var store: StoreEntity
}