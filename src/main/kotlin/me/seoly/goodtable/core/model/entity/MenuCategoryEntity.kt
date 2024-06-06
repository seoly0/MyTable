package me.seoly.goodtable.core.model.entity

import jakarta.persistence.*
import me.seoly.goodtable.core.Const
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.SQLRestriction
import me.seoly.spring.jpa.BaseEntity
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate

@Entity
@Table(
    name = "menu_category",
    indexes = []
)
@SQLDelete(sql = "UPDATE menu_category SET deleted = true WHERE id = ?")
@SQLRestriction("deleted = false")
@DynamicInsert
@DynamicUpdate
data class MenuCategoryEntity (
    @Column(name = "store_id")
    var storeId: Long,

    @Column(length = Const.COMMON_NAME_LENGTH)
    var name: String,
): BaseEntity() {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false, updatable = false, insertable = false)
    lateinit var store: StoreEntity
}